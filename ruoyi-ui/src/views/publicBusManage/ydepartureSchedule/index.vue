<template>
  <div class="app-container">
    <!--    顶部搜索框-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公交线路" prop="busno">
        <el-input
          v-model="queryParams.busno"
          placeholder="请输入公交车线路"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间间隔" prop="timeinterval">
        <el-input
          v-model="queryParams.timeinterval"
          placeholder="请输入时间间隔"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发车时间" prop="startime">
        <el-input
          v-model="queryParams.startime"
          placeholder="请输入发车时间"
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

    <!--    操作按钮-->
    <el-row :gutter="10" class="mb8">


      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['drivermanager:departureSchedule:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--    表格-->
    <el-table v-loading="loading" :data="departureScheduleList" @selection-change="handleSelectionChange">
      <!--      公交线路-->
      <el-table-column label="公交线路" align="center" prop="busno"/>
      <!--      发车时间-->
      <el-table-column label="发车时间" align="center" prop="startime"/>
      <!--      到站时间-->
      <el-table-column label="到站时间" align="center" prop="endtime"/>
      <!--      用时-->
      <el-table-column label="用时" align="center" prop="timeinterval"/>


    </el-table>

    <!--    上下页-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改发车时刻表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公交线路" prop="busno">
          <el-input v-model="form.busno" placeholder="请输入公交车路数"/>
        </el-form-item>
        <!--        发车时间-->
        <el-form-item label="发车时间" prop="startime">
          <el-input v-model="form.startime" placeholder="请输入发车时间"/>
        </el-form-item>
        <!--        到站时间-->
        <el-form-item label="到站时间" prop="endtime">
          <el-input v-model="form.endtime" placeholder="请输入到站时间"/>
        </el-form-item>
        <!--        用时-->
        <el-form-item label="用时" prop="timeinterval">
          <el-input v-model="form.timeinterval" placeholder="请输入时间间隔"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listDepartureSchedule,
  getDepartureSchedule,
  delDepartureSchedule,
  addDepartureSchedule,
  updateDepartureSchedule,
  exportDepartureSchedule,
  getHMS
} from "@/api/publicBusManage/departureSchedule/departureSchedule";

export default {
  name: "DepartureSchedule",

  data() {
    return {
      //新加的项
      timeStart: [],
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
      // 发车时刻表表格数据
      departureScheduleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busno: null,
        timeinterval: null,
        startime: null,
        endtime: null,
        attr1: null,
        attr2: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        busno: [
          {required: true, message: "公交车x路不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询发车时刻表列表 */
    getList() {
      this.loading = true;
      listDepartureSchedule(this.queryParams).then(response => {
        this.departureScheduleList = response.rows;
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
        scheduleid: null,
        busno: null,
        timeinterval: null,
        startime: null,
        endtime: null,
        attr1: null,
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
      this.ids = selection.map(item => item.scheduleid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加发车时刻表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scheduleid = row.scheduleid || this.ids
      getDepartureSchedule(scheduleid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发车时刻表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scheduleid != null) {
            updateDepartureSchedule(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDepartureSchedule(this.form).then(response => {
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
      const scheduleids = row.scheduleid || this.ids;
      this.$confirm('是否确认删除发车时刻表编号为"' + scheduleids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delDepartureSchedule(scheduleids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有发车时刻表数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportDepartureSchedule(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });

      // var num=24*60*60
      // var timeff= getHMS(num);
      // alert(timeff)

      listDepartureSchedule(this.queryParams).then(response => {
        this.departureScheduleList = response.rows;
        this.total = response.total;
        this.loading = false;

        for (var i = 0; i <= response.rows.length; i++) {
          console.log(response.rows[i].startime)
          this.timeStart = response.rows[i].startime * 60 * 60 - 20
          getHMS(this.timeStart)
          alert(getHMS(this.timeStart))

        }

      });

    },

  },

};

</script>
