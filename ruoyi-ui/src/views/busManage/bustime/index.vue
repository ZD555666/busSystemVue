<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="x路公交车" prop="busno">
        <el-input
          v-model="queryParams.busno"
          placeholder="请输入x路公交车"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时段" prop="timeinterval">
        <el-input
          v-model="queryParams.timeinterval"
          placeholder="请输入时段"
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
      <el-form-item label="到站时间" prop="endtime">
        <el-input
          v-model="queryParams.endtime"
          placeholder="请输入到站时间"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="到站时间" prop="attr1">-->
<!--        <el-input-->
<!--          v-model="queryParams.attr1"-->
<!--          placeholder="请输入到站时间"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="到站时间" prop="attr2">-->
<!--        <el-input-->
<!--          v-model="queryParams.attr2"-->
<!--          placeholder="请输入到站时间"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['busTime:bustime:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['busTime:bustime:edit']"
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
          v-hasPermi="['busTime:bustime:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['busTime:bustime:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bustimeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="scheduleid" />
      <el-table-column label="x路公交车" align="center" prop="busno" />
      <el-table-column label="时段" align="center" prop="timeinterval" />
      <el-table-column label="发车时间" align="center" prop="startime" />
      <el-table-column label="到站时间" align="center" prop="endtime" />
      <el-table-column label="车辆状态" align="center" prop="busstate" />
<!--      <el-table-column label="车牌" align="center" prop="licenseplate" />-->
<!--      <el-table-column label="到站时间" align="center" prop="attr1" />-->
<!--      <el-table-column label="到站时间" align="center" prop="attr2" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['busTime:bustime:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['busTime:bustime:remove']"
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

    <!-- 添加或修改公交车时刻对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
<!--        <el-form-item label="x路公交车" prop="busno">-->
<!--          <el-input v-model="form.busno" placeholder="请输入x路公交车" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="时段" prop="timeinterval">-->
<!--          <el-input v-model="form.timeinterval" placeholder="请输入时段" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="发车时间" prop="startime">-->
<!--          <el-input v-model="form.startime" placeholder="请输入发车时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="到站时间" prop="endtime">-->
<!--          <el-input v-model="form.endtime" placeholder="请输入到站时间" />-->
<!--        </el-form-item>-->
        <el-form-item label="车辆状态" prop="busstate">
          <el-input v-model="form.busstate" placeholder="请输入状态" />
        </el-form-item>
<!--        <el-form-item label="车牌" prop="licenseplate">-->
<!--          <el-input v-model="form.licenseplate" placeholder="请输入车牌" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="到站时间" prop="attr1">-->
<!--          <el-input v-model="form.attr1" placeholder="请输入到站时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="到站时间" prop="attr2">-->
<!--          <el-input v-model="form.attr2" placeholder="请输入到站时间" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBustime, getBustime, delBustime, addBustime, updateBustime, exportBustime } from "@/api/busMax/busTime/bustime";

export default {
  name: "Bustime",
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
      // 公交车时刻表格数据
      bustimeList: [],
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
          { required: true, message: "公交车x路不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公交车时刻列表 */
    getList() {
      this.loading = true;
      listBustime(this.queryParams).then(response => {
        this.bustimeList = response.rows;
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公交车时刻";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scheduleid = row.scheduleid || this.ids
      getBustime(scheduleid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公交车时刻";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scheduleid != null) {
            updateBustime(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBustime(this.form).then(response => {
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
      this.$confirm('是否确认删除公交车时刻编号为"' + scheduleids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBustime(scheduleids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有公交车时刻数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportBustime(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
