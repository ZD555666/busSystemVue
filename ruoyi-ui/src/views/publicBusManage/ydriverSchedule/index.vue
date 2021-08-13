<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工作班次" prop="workshift">
        <el-input
          v-model="queryParams.workshift"
          placeholder="请输入工作班次"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上班站点" prop="workstation">
        <el-input
          v-model="queryParams.workstation"
          placeholder="请输入上班站点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公交线路" prop="busno">
        <el-input
          v-model="queryParams.busno"
          placeholder="请输入公交线路"
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
          v-hasPermi="['driverManager:  driverSchedule:add']"
        >配置</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['driverManager:  driverSchedule:edit']"
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
          v-hasPermi="['driverManager:  driverSchedule:remove']"
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
          v-hasPermi="['driverManager:  driverSchedule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="  driverScheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公交线路" align="center" prop="busno" />
      <el-table-column label="上班站点" align="center" prop="workstation" />
      <el-table-column label="驾驶车辆" align="center" prop="licensePlate" />
      <el-table-column label="值班司机" align="center" prop="driverName" />
      <el-table-column label="工作班次" align="center" prop="workshift" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['driverManager:  driverSchedule:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['driverManager:  driverSchedule:remove']"
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

    <!-- 添加或修改司机排班对话框 -->
    <el-dialog :title="title" :visible.sync="open"  width="550px" style="height: 600px" append-to-body center>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公交线路" prop="busno">
          <el-autocomplete
            style="width: 280px"
            v-model="form.busno"
            :fetch-suggestions="querySearchAsyncLine"
            placeholder="请输入内容"
            @select="handleSelectLine"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="上班站点" prop="workstation">
          <el-autocomplete
            style="width: 280px"
            v-model="form.workstation"
            :fetch-suggestions="querySearchAsyncStation"
            placeholder="请输入内容"
            @select="handleSelectStation"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="驾驶车辆" prop="licensePlate">
          <el-autocomplete
            style="width: 280px"
            v-model="form.licensePlate"
            :fetch-suggestions="querySearchAsyncBus"
            placeholder="请输入内容"
            @select="handleSelectBus"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="值班司机" prop="driverName">
          <el-autocomplete
            style="width: 280px"
            v-model="form.driverName"
            :fetch-suggestions="querySearchAsyncDriver"
            placeholder="请输入内容"
            @select="handleSelectDriver"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="工作班次" prop="workshift">
          <el-select v-model="form.workshift" clearable placeholder="请选择">
            <el-option
              style="width: 280px"
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
import { selectBusDriverInformationList,selectBusPlateInformationList,selectBusnoInformationList,
  listdriverSchedule, getdriverSchedule, deldriverSchedule, adddriverSchedule, updatedriverSchedule,
  exportdriverSchedule,selectBusSchedule,addDialogInputInfo,
  selectStationInfolist } from "@/api/publicBusManage/driverSchedule/driverSchedule";
export default {
  name: "driverSchedule",
  components:{
    listdriverSchedule,
    getdriverSchedule,
    deldriverSchedule,
    adddriverSchedule,
    updatedriverSchedule,
    exportdriverSchedule,
    selectBusSchedule,
    addDialogInputInfo,
    selectStationInfolist ,
    selectBusnoInformationList,
    selectBusPlateInformationList,
    selectBusDriverInformationList
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
      // 司机排班表格数据
        driverScheduleList: [],
      // 弹出层标题
      title: "司机排班",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workshift: null,
        workstation: null,
        busno: null,
        userId: null,
        departureTime: null,
        driverName: null,
        licensePlate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //远程搜索框
      restaurantsTime: [],
      stateTime: '',
      timeoutTime:  null,

      restaurantsLine: [],
      stateLine: '',
      timeoutLine:  null,

      restaurantsStation: [],
      stateStation: '',
      timeoutStation:  null,

      restaurantsBus: [],
      stateBus: '',
      timeoutBus:  null,

      restaurantsDriver: [],
      stateDriver: '',
      timeoutDriver:  null,

    //  选择框
      options: [{
        value: '7-点早班',
        label: '7-点早班'
      }, {
        value: '12-点中班',
        label: '12-点中班'
      },
        {
          value: '14-点晚班',
          label: '14-点晚班'
        }],
      value: ''
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //新内容
    /** 查询司机排班列表 */
    getList() {
      this.loading = true;
      listdriverSchedule(this.queryParams).then(response => {
        this.driverScheduleList = response.rows;
        console.log("排班表"+response.rows)
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
        driverworkid: null,
        workshift: null,
        workstation: null,
        busno: null,
        userId: null,
        pageNum: 1,
        pageSize: 10,
        departureTime: null,
        driverName: null,
        licensePlate: null,
        stationName:null
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
      this.ids = selection.map(item => item.driverworkid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加司机排班";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const driverworkid = row.driverworkid || this.ids
      getdriverSchedule(driverworkid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改司机排班";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.driverworkid != null) {
            updatedriverSchedule(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            adddriverSchedule(this.form).then(response => {
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
      const driverworkids = row.driverworkid || this.ids;
      this.$confirm('是否确认删除司机排班编号为"' + driverworkids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deldriverSchedule(driverworkids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有司机排班数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportdriverSchedule(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },

  //  远程搜索框的内容
    //公交线路
    querySearchAsyncLine(queryString, cb) {
         var listno=[{}];
         selectBusnoInformationList(this.form).then(response=>{
                listno=response;

         })
      clearTimeout(this.timeoutLine);
      this.timeoutLine = setTimeout(() => {
        cb(listno);
      }, 3000 * Math.random());
    },

    //上班站点
    querySearchAsyncStation(queryString, cb) {
      var list=[{}];
      selectStationInfolist(this.form).then(response=>{
        console.log("站点信息"+response);
        list=response;
      });
      clearTimeout(this.timeoutStation);
      this.timeoutStation = setTimeout(() => {
        cb(list);
      }, 3000 * Math.random());
    },

    //驾驶车辆
    querySearchAsyncBus(queryString, cb) {
            var listPlate=[{}];
            selectBusPlateInformationList(this.form).then(response=>{
              listPlate=response;

            });
      clearTimeout(this.timeoutBus);
      this.timeoutBust = setTimeout(() => {
        cb(listPlate);
      }, 3000 * Math.random());
    },

    //值班司机
    querySearchAsyncDriver(queryString, cb) {
      var listDriver=[{}];
      selectBusDriverInformationList(this.form).then(response=>{
        listDriver=response;

      })

      clearTimeout(this.timeoutDriver);
      this.timeoutDriver = setTimeout(() => {
        cb(listDriver);
      }, 3000 * Math.random());
    },

    handleSelectLine(item) {
      console.log(item);
    },

    handleSelectStation(item) {
      console.log(item);
    },

    handleSelectBus(item) {
      console.log(item);
    },

    handleSelectDriver(item) {
      console.log(item);
    },


  }
};
</script>
