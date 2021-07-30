<template>

  <div class="app-container">
    <div style="background-color: #00afff;width: 100%;height: 20px;border-radius:30px">

    <img src="./images/圆圈.jpeg"style="width: 10px;height: 10px;border-radius:30px">


    </div>
    <img src="./images/公交车.jpeg"style="width: 20px;height: 10px;position: absolute;"id="img"><br>
<hr style="border: #1ab394 dashed">
    <img src="./images/公交车.jpeg"style="width: 20px;height: 10px;position: absolute;"id="img2"><br>
    <div style="background-color: #00afff;width: 100%;height: 20px;border-radius:30px" ></div>



    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="x路公交" prop="busno">
        <el-input
          v-model="queryParams.busno"
          placeholder="请输入x路公交"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="busrealrunList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="realrunid" />
      <el-table-column label="x路公交" align="center" prop="busno" />
      <el-table-column label="车牌" align="center" prop="licenseplate" />
      <el-table-column label="X坐标" align="center" prop="realxpoint" />
      <el-table-column label="Y坐标" align="center" prop="realypoint" />
      <el-table-column label="坐标" align="center" prop="realpoint" />
      <el-table-column label="当前站" align="center" prop="nowstation" />
      <el-table-column label="下一站" align="center" prop="nextstation" />
      <el-table-column label="路线状况" align="center" prop="roadstate" />
      <el-table-column label="行驶速度" align="center" prop="speed" />
      <el-table-column label="车内人数" align="center" prop="personnum" />
      <el-table-column label="车内人数" align="center" prop="demo1" />
      <el-table-column label="车内人数" align="center" prop="demo2" />
      <el-table-column label="车内人数" align="center" prop="demo3" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['busRealrun:busrealrun:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['busRealrun:busrealrun:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>

</template>

<script>

  import { listBusrealrun, getBusrealrun, delBusrealrun, addBusrealrun, updateBusrealrun, exportBusrealrun } from "@/api/busMax/busRealrun/busrealrun";
export default {
  name: "Busrealrun",

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
      // busRealrun表格数据
      busrealrunList: [],
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
        realxpoint: null,
        realypoint: null,
        realpoint: null,
        nowstation: null,
        nextstation: null,
        roadstate: null,
        speed: null,
        personnum: null,
        demo1: null,
        demo2: null,
        demo3: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {

    setInterval(function () {
      console.log(12312)
      var img = document.getElementById("img");
      img.style.left = img.offsetLeft + img.offsetWidth + "px";
    }, 500);
    setInterval(function () {
      console.log(22222)
      var img2 = document.getElementById("img2");
      img2.style.left = img2.offsetLeft - img2.offsetWidth + "px";
    }, 500);

    this.getList();
  },
  methods: {
    /** 查询busRealrun列表 */
    getList() {
      this.loading = true;
      listBusrealrun(this.queryParams).then(response => {
        this.busrealrunList = response.rows;
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
        realrunid: null,
        busno: null,
        licenseplate: null,
        realxpoint: null,
        realypoint: null,
        realpoint: null,
        nowstation: null,
        nextstation: null,
        roadstate: null,
        speed: null,
        personnum: null,
        demo1: null,
        demo2: null,
        demo3: null
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
      this.ids = selection.map(item => item.realrunid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加busRealrun";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const realrunid = row.realrunid || this.ids
      getBusrealrun(realrunid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改busRealrun";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.realrunid != null) {
            updateBusrealrun(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBusrealrun(this.form).then(response => {
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
      const realrunids = row.realrunid || this.ids;
      this.$confirm('是否确认删除busRealrun编号为"' + realrunids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delBusrealrun(realrunids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有busRealrun数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportBusrealrun(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    },
  }
};

</script>

<style type="text/css">
#img {
  position: absolute;
  top:7%;
  left: 0;
}
#img2 {
  position: absolute;
  top:13%;
  left:1320px;
}
</style>
