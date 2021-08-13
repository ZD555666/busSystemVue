<template>
  <div class="app-container">


    <busDeparture></busDeparture>


  </div>
</template>

<script>
import {
  listDepartureConfirm,
  getDepartureConfirm,
  delDepartureConfirm,
  addDepartureConfirm,
  updateDepartureConfirm,
  exportDepartureConfirm
} from "@/api/publicBusManage/departureConfirm/departureConfirm";
import busDeparture from "@/views/publicBusManage/quantifies/ymDepartureConfirm/busDeparture";

export default {
  name: "DepartureConfirm",
  components: {
    busDeparture
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公交车信息表格数据
      departureConfirmList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busno: null,
        licenseplate: null,
        busparam: null,
        roadid: null,
        attr2: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公交车信息列表 */
    getList() {
      this.loading = true;
      listDepartureConfirm(this.queryParams).then(response => {
        this.departureConfirmList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        busid: null,
        busno: null,
        licenseplate: null,
        busparam: null,
        roadid: null,
        attr2: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.busid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公交车信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const busid = row.busid || this.ids
      getDepartureConfirm(busid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公交车信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.busid != null) {
            updateDepartureConfirm(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDepartureConfirm(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const busids = row.busid || this.ids;
      this.$confirm('是否确认删除公交车信息编号为"' + busids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delDepartureConfirm(busids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有公交车信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportDepartureConfirm(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
