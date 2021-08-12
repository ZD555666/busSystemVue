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
          @click="addRoad(0)"
        >新增</el-button>
      </el-col>
      <right-toolbar @queryTable="getList"></right-toolbar>
    </el-row>
<!--表格数据栏-->
    <el-table v-loading="loading" :data="roadsList">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="城市名" align="center" prop="cityName"/>
      <el-table-column label="公交车线路编号" align="center" prop="busNo" />
      <el-table-column label="经停站点数(启程,返程)" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="viewUpdateRoad(1,scope.row)"
          >{{ scope.row.rsListSize }} , {{ scope.row.rsReListSize }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="每日运行班次数(次)" align="center">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          icon="el-icon-timer" openSchedule
          @click="openSchedule(scope.row)"
        >{{ scope.row.scheduleListSize }}
        </el-button>
      </template>
      </el-table-column>
      <el-table-column label="线路在用车辆数(辆)" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-truck"
            @click="openBuses(scope.row)"
          >{{ scope.row.busListSize }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="花费(元)" align="center" prop="cost"/>
      <el-table-column label="单程时间(分钟)" align="center" prop="travelTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="deleteRoad(scope.row)"
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
<!--    车辆数据弹窗-->
    <el-dialog
      title="车辆查看"
      :visible.sync="busVisible"
      width="30%"
      :before-close="closeBuses">
      <br>
      <el-card class="box-card">
        <div slot="header" class="clearfix">

          <span v-if="oneRoadInfo!==null">线路编号：{{ oneRoadInfo.busNo }}</span>
        </div>
        <div v-for="bus in buses" class="text item">
            {{bus.licensePlate }}
        </div>
      </el-card>
      <span slot="footer" class="dialog-footer">
    <el-button @click="busVisible = false">取 消</el-button>
    <el-button type="primary" @click="busVisible = false">确 定</el-button>
  </span>
    </el-dialog>
<!--    时间表弹窗容器-->
    <el-dialog
      title="时间查看"
      :visible.sync="scheduleVisible"
      width="30%"
      :before-close="closeSchedule">
      <!--时间表-->
      <view-schedule></view-schedule>
      <span slot="footer" class="dialog-footer">
    <el-button @click="scheduleVisible = false">取 消</el-button>
    <el-button type="primary" @click="scheduleVisible = false">确 定</el-button>
    </span>
    </el-dialog>
<!--    新增页面-->

<!--      添加线路-->
    <add-road ref="child1" @fatherMethod="getList"></add-road>
<!--确认删除弹窗-->
<!--    <el-dialog-->
<!--      title="提示"-->
<!--      :visible.sync="openConfirmDelete"-->
<!--      width="30%">-->
<!--      <span>确定删除{{}}?</span>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--    <el-button @click="openConfirmDelete = false">取 消</el-button>-->
<!--    <el-button type="primary" @click="openConfirmDelete = false">确 定</el-button>-->
<!--    </span>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import {deleteRoad, delRoads, listRoads} from "@/api/road/roads";
import viewSchedule from "@/views/road/roads/viewSchedule";
import addRoad from '@/views/road/roads/addRoad';
import getStation from '@/views/road/roads/getStation';

export default {
  name: "Roads",
  components:{viewSchedule, addRoad, getStation},
  data() {
    return {
      //判断弹窗里的内容是新增还是更新
      dialogType:0,
      //点击删除后保存的数据
      deleteList:[],
      //打开确认删除弹窗
      openConfirmDelete:false,
      //打开新增弹窗
      openAddRoad: false,
      //一条线路的信息
      oneRoadInfo:[{
        busNo: ''
      }],
      //时间表弹窗是否可见
      scheduleVisible:false,
      //时间集合
      schedules:[],
      //车辆弹窗是否可见
      busVisible:false,
      // 线路在用车辆
      buses:[{
        busNo:''
      }],
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 线路配置表格数据
      roadsList: [{busNo: ''}],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busNo: null,
        cityName: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 新增线路 */
    addRoad(type){
      this.$refs.child1.parentClick(type);
    },
    /** 查看已配置的线路并可修改 */
    viewUpdateRoad(type,roadList) {
      this.openAddRoad=true;
      console.log("父页面", this.$refs.child1);
      this.$refs.child1.parentClick(type, roadList);
    },
    /** 删除线路 */
    deleteRoad(roadList){
      console.log("删除的集合", roadList);
      let params={
        cityId: roadList.cityId,
        busNo:roadList.busNo
      }
      deleteRoad(params).then(response => {
        this.getList();
        alert(response);
      });

    },

    /** 关闭新增页面 */
    closeAdd(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
          //初始化启反程数据列表
          this.$store.commit("roadInfo/clearRoadStation");
        })
        .catch(_ => {
        });
    },
    /** 关闭时间弹窗 */
    closeSchedule(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    /** 打开时间弹窗 */
    openSchedule(row) {
      this.schedules = row.scheduleList;
      this.oneRoadInfo= row;
      console.log("时间表信息数据");
      console.log(this.schedules);
      console.log("vuex数据存储this.$store.state.roadInfo.oneRoadInfo");
      this.$store.commit('roadInfo/addOneRoadInfo', row);
      console.log(this.$store.state.roadInfo.oneRoadInfo);
      this.scheduleVisible = true;
    },
    /** 关闭车辆弹窗 */
    closeBuses(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    /** 打开车辆弹窗 */
    openBuses(row){
      this.buses = row.busList;
      this.oneRoadInfo = row;
      console.log("车辆信息数据", this.buses);
      this.$store.commit('roadInfo/addOneRoadInfo', row);
      console.log("vuex数据存储oneRoadInfo",this.$store.state.roadInfo.oneRoadInfo);
      this.busVisible = true;
    },
    /** 查询线路配置列表 */
    getList() {
      this.loading = true;
      listRoads(this.queryParams).then(response => {
        this.roadsList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log("获取的分页线路数据集合",this.roadsList);
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

<style>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
