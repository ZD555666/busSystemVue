<template>
  <div id="addRoad">
    <el-form ref="form" :model="form" label-width="80px">
      <el-row>
        <el-col :span="10">
          <el-form-item label="城市：">
            <el-select v-model="form.cityId" placeholder="请选择城市" >
              <el-option v-for="city in cityList" :label="city.cityname" :value="city.cityid" :key="city.cityid" ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="线路名：">
            <el-input v-model="form.roadNo"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10">
          <el-form-item label="花费：">
            <el-input v-model="form.cost"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="时长：">
            <el-input v-model="form.time"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10">
          <el-form-item label="始发站：" >
            <el-input :disabled="true" :value="this.$store.state.roadInfo.stationSort[0].stationName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-button type="primary" plain icon="el-icon-s-flag" @click="openStationSelect = true,openMapType= 1">地图选择</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10">
          <el-form-item label="终点站：">
            <el-input :disabled="true" :value="this.$store.state.roadInfo.stationSort[this.$store.state.roadInfo.stationSort.length - 1].stationName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-button type="primary" plain icon="el-icon-s-flag" @click="openStationSelect = true,openMapType= 0">地图选择</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" class="grid-content">
          <div>
            <el-row>
              <label>线路配置：</label>
            </el-row>
            <el-row>
              <el-col :span="10" class="grid-content">
                <div style="height: 400px;border: black solid 1px;overflow-y:auto">
                  <!--启程      -->
                  <div v-for="(item, index) in stationSort" v-if="item.stationName!=''">
                    <div>
                      <span class="fontStyle">{{index+1}}、</span>
                      <span class="fontStyle" style="margin-right: 20px">{{ item.stationName }}</span>
                      <el-button type="primary" plain icon="el-icon-circle-plus-outline" @click="addStation(index)">添加
                      </el-button>
                      <el-button type="danger" plain icon="el-icon-circle-close" @click="deleteStation(index)">删除</el-button>
                    </div>
                    <div>
                      <img src="../../../assets/images/arrow.png" alt="" class="arrowStyle"/>
                    </div>
                  </div>
                </div>
                <div>
                  <el-button type="primary" plain @click="openPreviewRoad(0)" style="margin: 10px;" icon="el-icon-share">
                    启程线路预览
                  </el-button>
                </div>
              </el-col>
              <el-col :span="4" class="grid-content">
                <el-button type="primary" plain style="margin-left: 10px;margin-top: 60px" @click="createReturn()">生成反向>></el-button>
              </el-col>
              <el-col :span="10" class="grid-content">
                <div style="height: 400px;border: black solid 1px;overflow-y:auto">
                  <!--返程      -->
                  <div v-for="(item, index) in returnSort" v-if="item.stationName!=''">
                    <div>
                      <span class="fontStyle">{{ index + 1 }}、</span>
                      <span class="fontStyle" style="margin-right: 20px">{{ item.stationName }}</span>
                      <el-button type="primary" plain icon="el-icon-circle-plus-outline" @click="addReturn(index)">添加
                      </el-button>
                      <el-button type="danger" plain icon="el-icon-circle-close" @click="deleteReturn(index)">删除
                      </el-button>
                    </div>
                    <div>
                      <img src="@/assets/images/arrow.png" alt="" class="arrowStyle"/>
                    </div>
                  </div>
                </div>
                <div>
                  <el-button type="primary" plain @click="openPreviewRoad(1)" style="margin: 10px;" icon="el-icon-share" >
                    返程线路预览
                  </el-button>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :span="20" class="grid-content">

        </el-col>
      </el-row>
      <el-row>
        <el-form-item>
          <el-button type="primary" @click="confirmSubmit">立即创建</el-button>
<!--          <el-button>取消</el-button>-->
        </el-form-item>
      </el-row>
    </el-form>
<!--    地图站点选择弹窗-->
    <el-dialog
      title="地图站点选择"
      :visible.sync="openStationSelect"
      width="80%"
      :before-close="closeStationSelect">
      <el-row>
        <el-col :span="6">
          <div class="inputStyle"><label>经度:</label><el-input :disabled="true" :value="stationSelect.xPoint"></el-input></div>
        </el-col>
        <el-col :span="6">
          <div class="inputStyle"><label>纬度:</label><el-input :disabled="true" :value="stationSelect.yPoint"></el-input></div>
        </el-col>
        <el-col :span="6">
          <div class="inputStyle"><label>站点名:</label><el-input :disabled="true" :value="stationSelect.stationName"></el-input></div>
        </el-col>
      </el-row>
      <el-row>
        <get-station></get-station>
      </el-row>
      <span slot="footer" class="dialog-footer">
          <el-button @click="openStationSelect = false">取 消</el-button>
          <el-button type="primary" @click="confirmChoice()">确 定</el-button>
      </span>
    </el-dialog>
<!--   线路预览-->
    <el-dialog
      title="线路预览"
      :visible.sync="previewRoad"
      width="50%">
    <showRoad></showRoad>
    </el-dialog>
  </div>
</template>

<script>
import getStation from '@/views/road/roads/getStation';
import {addRoads, cityOption, listRoads} from "@/api/road/roads";
import {createNamespacedHelpers} from 'vuex'
const {mapState, mapActions} = createNamespacedHelpers('roadInfo')
import showRoad from '@/views/road/roads/showRoad'

export default {
  name: "addRoad",
  components:{getStation, showRoad},
  data() {
    return {
      //预览线路弹窗
      previewRoad: false,
      //站点顺序
      stationIndex:0,
      //打开地图类型
      openMapType:-1,
      //挑选站点弹窗
      openStationSelect:false,
      //城市下拉框数据
      cityList: [],
      form: {
        cityId: '',
        roadNo: '',
        cost: '',
        time: '',
        stationPositive: '',
        stationReverse: ''
      }
    }
  },
  created() {
    this.getCityList();
  },
  computed: {
    ...mapState({
      stationSelect: state => state.stationSelect,
      stationSort: state => state.stationSort,
      returnSort: state => state.returnSort
    })
  },
  methods: {
    /** 提交表单 */
    confirmSubmit(){
      this.form.stationPositive= JSON.stringify(this.$store.state.roadInfo.stationSort);
      this.form.stationReverse = JSON.stringify(this.$store.state.roadInfo.returnSort);
      let map={
        cityId: this.form.cityId,
        roadNo: this.form.roadNo,
        cost: this.form.cost,
        time: this.form.time,
        stationPositive: this.form.stationPositive,
        stationReverse: this.form.stationReverse
      }
      addRoads(map).then(response => {
        alert(response);
      });
      //初始化启反程数据列表
      this.$store.commit("roadInfo/clearRoadStation");
    },
    /** 打开预览线路 */
    openPreviewRoad(roadType) {
      if(roadType==0){
        this.$store.commit("roadInfo/addMapRoadSort");
        this.$store.commit("roadInfo/addMapStationSort");
      }else if (roadType==1){
        this.$store.commit("roadInfo/addReturnMapRoad");
        this.$store.commit("roadInfo/addReturnMapStation");
      }
      this.previewRoad = true;
    },
    /**
     * 添加反向路线某站点
     * */
    addReturn(returnIndex){
      this.openMapType = 3;
      this.openStationSelect = true;
      this.stationIndex = returnIndex;
    },
    /**
     * 删除反向路线某站点
     * */
    deleteReturn(returnIndex){
      this.$store.commit('roadInfo/deleteReturn', returnIndex);
    },
    /**
     * 生成反向路线
     * */
    createReturn(){
      this.$store.commit('roadInfo/createReturn');
    },
    /**
     * 点击删除站点
     * */
    deleteStation(stationIndex){
      this.$store.commit('roadInfo/deleteStation', stationIndex);
    },
    /**
     * 点击添加站点
     * */
    addStation(stationIndex){
      this.openMapType=2;
      this.openStationSelect=true;
      this.stationIndex= stationIndex;
    },
    /**
     * 确定站点选择
     * */
    confirmChoice(){
      if (this.openMapType== 0){
        this.$store.commit('roadInfo/addLastStation');
      }else if (this.openMapType == 1){
        this.$store.commit('roadInfo/addFirstStation');
      }else if (this.openMapType == 2){
        this.$store.commit('roadInfo/addStation',this.stationIndex);
      }else if (this.openMapType==3){
        this.$store.commit('roadInfo/addReturn', this.stationIndex);
      }
      this.openStationSelect = false;
    },

    /**
     * 关闭站点选择弹窗
     * */
    closeStationSelect(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    /**
     * 后台城市集合并赋值
     * */
    getCityList() {
      this.loading = true;
      cityOption().then(response => {
        this.cityList = response;
        console.log("获取城市下拉框数据",this.cityList);
      });
    }
  }
}
</script>

<style>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.inputStyle{
  margin-left: 10px;
  margin-bottom: 5px;
}
.fontStyle{
  color: #53A8FF;
  font-size: 18px;
}
.arrowStyle{
  width: 50px;
  height: 50px;
}
</style>
