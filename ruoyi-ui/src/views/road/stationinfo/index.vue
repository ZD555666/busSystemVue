<template>
<!--  修改第一次-->
<!--  修改第二次-->
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="城市名" prop="cityname">
        <el-input
          v-model="queryParams.cityname"
          placeholder="请输入城市名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市id" prop="cityid">
        <el-input
          v-model="queryParams.cityid"
          placeholder="请输入城市id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点名称" prop="stationname">
        <el-input
          v-model="queryParams.stationname"
          placeholder="请输入站点名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经纬度" prop="point">
        <el-input
          v-model="queryParams.point"
          placeholder="请输入经纬度"
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
          icon="el-icon-s-flag"
          size="mini"
          @click="showStation = true"
        >查看已有站点
        </el-button>
        <el-dialog
          title="当前已有站点显示"
          :visible.sync="showStation"
          width="90%"
          top="0px"
          :before-close="mapStationClose">
          <div style="width: 100%;height: 550px">
            <!--            查看站点容器 -->
            <showStation></showStation>
            <!--            查看站点容器 -->
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="showStation = false">取 消</el-button>
            <el-button type="primary" @click="showStation = false">确 定</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="1.5">
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['road:stationinfo:add']"
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
          v-hasPermi="['road:stationinfo:edit']"
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
          v-hasPermi="['road:stationinfo:remove']"
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
          v-hasPermi="['road:stationinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="城市名称" align="center" prop="city.cityname"/>
      <el-table-column label="城市id" align="center" prop="cityid" />
      <el-table-column label="站点名称" align="center" prop="stationname" />
      <el-table-column label="经度" align="center" prop="xpoint" />
      <el-table-column label="纬度" align="center" prop="ypoint" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['road:stationinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['road:stationinfo:remove']"
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

    <!-- 添加或修改站点信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-button
        type="primary"
        plain
        icon="el-icon-location-information"
        size="mini"
        @click="dialogVisible = true"
      style="margin: 10px" >选择经纬度
      </el-button>
      <el-dialog
        title="地图选点"
        :visible.sync="dialogVisible"
        width="90%"
        top="0px">
        <div style="width: 100%;height: 550px">
          <!--            查询坐标容器 -->
          <getLocation></getLocation>
          <!--            查询坐标容器 -->
        </div>
<!--        <span slot="footer" class="dialog-footer">-->
<!--            <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
<!--        </span>-->
      </el-dialog>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="城市id" prop="cityid">
          <el-input v-model="form.cityid" placeholder="请输入城市id" />
        </el-form-item>
        <el-form-item label="站点名称" prop="stationname">
          <el-input v-model="form.stationname" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="经度" prop="xpoint">
          <el-input v-model="form.xpoint"/>
        </el-form-item>
        <el-form-item label="纬度" prop="ypoint">
          <el-input v-model="form.ypoint" />
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
import { listStationinfo, getStationinfo, delStationinfo, addStationinfo, updateStationinfo, exportStationinfo } from "@/api/road/stationinfo";
import getLocation from './getLocation.vue';
import showStation from './showStation'
export default {
  name: "Stationinfo",
  components: {getLocation, showStation},
  data() {
    return {
      //地图站点显示弹出层
      showStation:false,
      // 地图选点弹出层
      dialogVisible: false,
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
      // 站点信息表格数据
      stationinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cityid: null,
        stationname: null,
        xpoint: null,
        ypoint: null,
        point: null,
        cityname: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  mounted() {
  },
  created() {
    this.getList();
  },
  methods: {
    mapStationClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    /** 地图弹出层*/
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    /** 查询站点信息列表 */
    getList() {
      this.loading = true;
      listStationinfo(this.queryParams).then(response => {
        this.stationinfoList = response.rows;
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
        stationid: null,
        cityid: null,
        stationname: null,
        xpoint: null,
        ypoint: null,
        point: null
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
    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stationid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加站点信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stationid = row.stationid || this.ids
      getStationinfo(stationid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改站点信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stationid != null) {
            updateStationinfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStationinfo(this.form).then(response => {
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
      const stationids = row.stationid || this.ids;
      this.$confirm('是否确认删除站点信息编号为"' + stationids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStationinfo(stationids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有站点信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportStationinfo(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>

<style>
.bm-view {
  width: 100%;
  height: 300px;
}
</style>


