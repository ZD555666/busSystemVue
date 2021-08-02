<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
<!--  搜索栏-->
      <el-form-item label="公交线路" prop="busNo">
        <el-input
          v-model="queryParams.busNo"
          placeholder="请输入公交车线路"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市名" prop="cityName">
        <el-input
          v-model="queryParams.cityName"
          placeholder="请输入城市名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
<!--    表格操作栏-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
        >新增</el-button>
      </el-col>
    </el-row>
<!--表格数据栏-->
    <el-table v-loading="loading" :data="roadsList">
      <el-table-column type="index" label="数据编号" align="center"/>
      <el-table-column label="城市名" align="center" prop="cityName"/>
      <el-table-column label="公交车线路编号" align="center" prop="busNo" />
      <el-table-column label="经停站点数(启程,返程)" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="test"
          >{{ scope.row.stationListSize }} , {{ scope.row.stationReListSize }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="每日运行班次数" align="center">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          icon="el-icon-timer"
          @click="test"
        >{{ scope.row.scheduleListSize }}
        </el-button>
      </template>
      </el-table-column>
      <el-table-column label="线路在用车辆数" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-truck"
            @click="test"
            v-hasPermi="['road:roads:edit']"
          >{{ scope.row.busListSize }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="花费" align="center" prop="cost"/>
      <el-table-column label="单程时间" align="center" prop="travelTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
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
import { listRoads } from "@/api/road/roads";

export default {
  name: "Roads",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 线路配置表格数据
      roadsList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busNo: null,
        cityName: null,
        cityId:0,
        stationListSize:0,
        stationReListSize: 0,
        scheduleListSize:0,
        busListSize:0
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    test(){
      alert("dianji");
    },
    /** 查询线路配置列表 */
    getList() {
      this.loading = true;
      listRoads(this.queryParams).then(response => {
        this.roadsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    }
  }
};
</script>
