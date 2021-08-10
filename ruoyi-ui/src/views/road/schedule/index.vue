<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公交线路：" prop="busNo" label-width="90px">
        <el-input
          v-model="queryParams.busNo"
          placeholder="请输入公交线路"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发车时间段(前)" prop="starTime" label-width="150px">
        <el-input
          v-model="queryParams.starTime"
          placeholder="请输入发车时间段(前)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发车时间段(后)" prop="endTime" label-width="150px">
        <el-input
          v-model="queryParams.endTime"
          placeholder="请输入发车时间段(后)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="各个时间段发车间隔时间：" prop="timeInterval" label-width="200px">
        <el-input
          v-model="queryParams.timeInterval"
          placeholder="请输入各个时间段发车间隔时间"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['road:schedule:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
        >新增多时段
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['road:schedule:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['road:schedule:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--		  :loading="exportLoading"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['road:schedule:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公交线路" align="center" prop="busNo" />
      <el-table-column label="发车时间段(前)" align="center" prop="starTime" />
      <el-table-column label="发车时间段(后)" align="center" prop="endTime" />
      <el-table-column label="各个时间段发车间隔时间" align="center" prop="timeInterval"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['road:schedule:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['road:schedule:remove']"
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

    <!-- 添加或修改线路发车时刻配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公交线路：" prop="busNo" label-width="100px">
          <el-input v-model="form.busNo" placeholder="请输入公交线路" />
        </el-form-item>
        <el-form-item label="时间间隔：" prop="timeInterval" label-width="100px">
          <el-input v-model="form.timeInterval" placeholder="请输入各个时间段发车时间" />
        </el-form-item>
        <el-form-item label="发车时间段：(前)" prop="starTime" label-width="100px">
          <el-input v-model="form.starTime" placeholder="请输入发车时间段(前)" />
        </el-form-item>
        <el-form-item label="发车时间段：(后)" prop="endTime" label-width="100px">
          <el-input v-model="form.endTime" placeholder="请输入发车时间段(后)" />
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
import { listSchedule, getSchedule, delSchedule, addSchedule, updateSchedule, exportSchedule } from "@/api/road/schedule";

export default {
  name: "Schedule",
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
      // 线路发车时刻配置表格数据
      scheduleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busNo: null,
        timeInterval: null,
        starTime: null,
        endTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        busNo: [
          { required: true, message: "公交线路不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询线路发车时刻配置列表 */
    getList() {
      this.loading = true;
      listSchedule(this.queryParams).then(response => {
        this.scheduleList = response.rows;
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
        scheduleId: null,
        busNo: null,
        timeInterval: null,
        starTime: null,
        endTime: null,
        busState: null
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
      this.ids = selection.map(item => item.scheduleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加线路发车时刻配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scheduleId = row.scheduleId || this.ids
      getSchedule(scheduleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改线路发车时刻配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scheduleId != null) {
            updateSchedule(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSchedule(this.form).then(response => {
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
      const scheduleIds = row.scheduleId || this.ids;
      this.$confirm('是否确认删除线路发车时刻配置编号为"' + scheduleIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSchedule(scheduleIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有线路发车时刻配置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportSchedule(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
