<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="sys_user的司机id" prop="userId">-->
<!--        <el-input-->
<!--          v-model="queryParams.userId"-->
<!--          placeholder="请输入sys_user的司机id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="当天日期, t_driverId 记录每个司机的工作量" prop="busdriverid">-->
<!--        <el-input-->
<!--          v-model="queryParams.busdriverid"-->
<!--          placeholder="请输入当天日期, t_driverId 记录每个司机的工作量"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="工作时长" prop="driverworktime">
        <el-input
          v-model="queryParams.driverworktime"
          placeholder="请输入工作时长"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上班打卡" prop="atworkpunch">
        <el-date-picker clearable size="small"
          v-model="queryParams.atworkpunch"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择上班打卡">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="下班打卡" prop="offdutypunch">
        <el-date-picker clearable size="small"
          v-model="queryParams.offdutypunch"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择下班打卡">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发车打卡" prop="gobuspunch">
        <el-input
          v-model="queryParams.gobuspunch"
          placeholder="请输入发车打卡"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="备用字段" prop="attr1">-->
<!--        <el-input-->
<!--          v-model="queryParams.attr1"-->
<!--          placeholder="请输入备用字段"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="备用字段" prop="attr2">-->
<!--        <el-input-->
<!--          v-model="queryParams.attr2"-->
<!--          placeholder="请输入备用字段"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <div style="position: absolute;left: 166px;top: 0px"><router-link to="/ymDriverSalary"><el-button type="info" icon="el-icon-s-goods" size="mini" >工资查看</el-button></router-link></div>
        <div style="position: absolute;left: 274px;top: 0px"><router-link to="/workPunchIn"><el-button type="warning" icon="el-icon-time" size="mini" >上班打卡</el-button></router-link></div>
        <div style="position: absolute;left: 383px;top: 0px"><router-link to="/ymDepartureConfirm"><el-button type="warning" icon="el-icon-user" size="mini" >出站确认</el-button></router-link></div>
      </el-form-item>
    </el-form>

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['quantifies:quantifies:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['quantifies:quantifies:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['quantifies:quantifies:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--		  :loading="exportLoading"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['quantifies:quantifies:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

    <el-table v-loading="loading" :data="quantifiesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="仅主键，无意义" align="center" prop="id" />-->
<!--      <el-table-column label="sys_user的司机id" align="center" prop="userId" />-->
<!--      <el-table-column label="当天日期, t_driverId 记录每个司机的工作量" align="center" prop="busdriverid" />-->
      <el-table-column label="工作时长" align="center" prop="driverworktime" />
      <el-table-column label="上班打卡" align="center" prop="atworkpunch" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.atworkpunch, '{y}-{m}-{d} ') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下班打卡" align="center" prop="offdutypunch" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.offdutypunch, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发车打卡" align="center" prop="gobuspunch" />
<!--      <el-table-column label="备用字段" align="center" prop="attr1" />-->
<!--      <el-table-column label="备用字段" align="center" prop="attr2" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['quantifies:quantifies:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['quantifies:quantifies:remove']"
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

    <!-- 添加或修改司机工作时间对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="sys_user的司机id" prop="userId">-->
<!--          <el-input v-model="form.userId" placeholder="请输入sys_user的司机id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="当天日期, t_driverId 记录每个司机的工作量" prop="busdriverid">-->
<!--          <el-input v-model="form.busdriverid" placeholder="请输入当天日期, t_driverId 记录每个司机的工作量" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="工作时长" prop="driverworktime">-->
<!--          <el-input v-model="form.driverworktime" placeholder="请输入工作时长" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="上班打卡" prop="atworkpunch">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.atworkpunch"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择上班打卡">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="下班打卡" prop="offdutypunch">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.offdutypunch"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择下班打卡">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="发车打卡" prop="gobuspunch">-->
<!--          <el-input v-model="form.gobuspunch" placeholder="请输入发车打卡" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备用字段" prop="attr1">-->
<!--          <el-input v-model="form.attr1" placeholder="请输入备用字段" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备用字段" prop="attr2">-->
<!--          <el-input v-model="form.attr2" placeholder="请输入备用字段" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listQuantifies, getQuantifies, delQuantifies, addQuantifies, updateQuantifies, exportQuantifies } from "@/api/publicBusManage/quantifies/quantifies";

export default {
  name: "Quantifies",
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
      // 司机工作时间表格数据
      quantifiesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        busdriverid: null,
        driverworktime: null,
        atworkpunch: null,
        offdutypunch: null,
        gobuspunch: null,
        sysUserId: null,
        tDriverId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询司机工作时间列表 */
    getList() {
      this.loading = true;
      listQuantifies(this.queryParams).then(response => {
        this.quantifiesList = response.rows;
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
        id: null,
        userId: null,
        busdriverid: null,
        driverworktime: null,
        atworkpunch: null,
        offdutypunch: null,
        gobuspunch: null,
        sysUserId: null,
        tDriverId: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加司机工作时间";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getQuantifies(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改司机工作时间";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateQuantifies(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuantifies(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除司机工作时间编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQuantifies(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有司机工作时间数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportQuantifies(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
