<template>
  <div id="addRoad">
    <el-dialog :title="dialogTitle" :visible.sync="openAddRoad" width="60%" :before-close="closeAdd">
    <el-form ref="form" :model="form" label-width="80px">
<!--      :rules="rules"-->
      <el-row>
        <el-col :span="10">
          <el-form-item label="城市：">
            <el-select v-model="form.cityId" placeholder="请选择城市" id="city" :disabled="inputAble.cityAble">
              <el-option v-for="city in cityList" :label="city.cityname" :value="city.cityid" :key="city.cityid" ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="线路名：">
            <el-input v-model="form.roadNo" id="roadNo" :disabled="inputAble.roadNoAble"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10">
          <el-form-item label="花费(元)：" id="cost" >
            <el-input v-model="form.cost" :disabled="inputAble.costAble"></el-input>
          </el-form-item>
        </el-col>
<!--        <el-col :span="10">-->
<!--          <el-form-item label="时长(分钟)：" id="time"  label-width="120px">-->
<!--            <el-input v-model="form.time"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
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
                    <div v-if="index>0">
                      <img src="../../../assets/images/arrow.png" alt="" class="arrowStyle"/>
                      <span style="font-size: 18px;margin-right: 10px">{{ item.distance }}</span>
                      <span style="font-size: 18px">预计时长：{{ item.time }}</span>
                    </div>
                    <div>
                      <span class="fontStyle">{{index+1}}、</span>
                      <span class="fontStyle" style="margin-right: 5px">{{ item.stationName }}</span>
                      <el-button type="primary" plain icon="el-icon-circle-plus-outline" @click="addStation(index)">添加
                      </el-button>
                      <el-button type="danger" plain icon="el-icon-circle-close" @click="deleteStation(index)">删除</el-button>
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
                    <div v-if="index>0">
                      <img src="../../../assets/images/arrow.png" alt="" class="arrowStyle"/>
                      <span style="font-size: 18px;margin-right: 10px">{{ item.distance }}</span>
                      <span style="font-size: 18px">预计时长：{{ item.time }}</span>
                    </div>
                    <div>
                      <span class="fontStyle">{{ index + 1 }}、</span>
                      <span class="fontStyle" style="margin-right: 5px">{{ item.stationName }}</span>
                      <el-button type="primary" plain icon="el-icon-circle-plus-outline" @click="addReturn(index)">添加
                      </el-button>
                      <el-button type="danger" plain icon="el-icon-circle-close" @click="deleteReturn(index)">删除
                      </el-button>
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
          <el-button type="primary" v-if="newVisible" @click="confirmSubmit(0)">立即创建</el-button>
          <el-button type="primary" v-if="updateVisible" @click="confirmSubmit(1)">确认修改</el-button>
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
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelAdd">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import getStation from '@/views/road/roads/getStation';
import {addRoads, cityOption, listRoads, updateRoads} from "@/api/road/roads";
import {createNamespacedHelpers} from 'vuex'
const {mapState, mapActions} = createNamespacedHelpers('roadInfo')
import showRoad from '@/views/road/roads/showRoad'

export default {
  name: "addRoad",
  components:{getStation, showRoad},
  data() {
    return {
      //表单文字是否可编辑
      inputAble:{
        cityAble:false,
        roadNoAble:false,
        costAble:false,
        timeAble:false
      },
      //新建线路的按钮显隐
      newVisible:false,
      //更新线路的按钮显隐
      updateVisible:false,
      //弹窗的标题
      dialogTitle:'',
      //打开新增弹窗
      openAddRoad: false,
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
      },
      rules:{
        cityId:[
          {required: true, message: "城市不能为空", trigger: "blur"}
        ],
        roadNo: [
          {required: true, message: "城市不能为空", trigger: "blur"}
        ],
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
      returnSort: state => state.returnSort,
      timeCount: state => state.timeCount

    })
  }
  ,
  methods: {
    /** 取消新增 */
    cancelAdd() {
      //初始化启反程数据列表
      this.openAddRoad = false;
      this.initFrom();
    },
    /** 关闭新增页面 */
    closeAdd(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
          //初始化启反程数据列表
          this.initFrom();
        })
        .catch(_ => {
        });
    },
    /** 查看已配置的线路并可修改 */
    parentClick(type, roadList) {
      this.openAddRoad = true;
      if (type==0){
        this.dialogTitle = '新增线路';
        this.newVisible=true;
        this.updateVisible=false;
        this.inputAble.cityAble = false;
        this.inputAble.roadNoAble = false;
        // this.inputAble.costAble = false;
        // this.inputAble.timeAble = false;
      } else if (type==1){
        this.dialogTitle='查看和修改线路';
        this.newVisible = false;
        this.updateVisible = true;
        this.inputAble.cityAble = true;
        this.inputAble.roadNoAble = true;
        // this.inputAble.costAble=true;
        // this.inputAble.timeAble = true;
        this.form.cityId= roadList.cityId;
        this.form.roadNo= roadList.busNo;
        this.form.cost= roadList.cost;
        this.form.time= roadList.travelTime;
        this.$store.commit("roadInfo/viewRoadStation", roadList);
        this.$store.commit("roadInfo/getResource");
      }

      console.log("子页面", type);
      console.log("子页面", roadList);
    },
    /** 提交表单 */
    confirmSubmit(type){
      if (this.form.cityId!=''&& this.form.roadNo != '' && this.form.cost != ''){
        this.form.stationPositive = JSON.stringify(this.$store.state.roadInfo.stationSort);
        this.form.stationReverse = JSON.stringify(this.$store.state.roadInfo.returnSort);
        let map = {
          cityId: this.form.cityId,
          roadNo: this.form.roadNo,
          cost: this.form.cost,
          time: this.timeCount,
          stationPositive: this.form.stationPositive,
          stationReverse: this.form.stationReverse,
          actionType: 0
        }
        let map2 = {
          cityId: this.form.cityId,
          roadNo: this.form.roadNo,
          cost: this.form.cost,
          time: this.timeCount,
          stationPositive: this.form.stationPositive,
          stationReverse: this.form.stationReverse,
          actionType: 1
        }
        if (type == 0) {
          addRoads(map).then(response => {
            this.$emit('fatherMethod');
            this.openAddRoad = false;
            alert(response);
          });
        } else if (type == 1) {
          addRoads(map2).then(response => {
            this.$emit('fatherMethod');
            this.openAddRoad = false;
            alert(response);
          });
        }
        this.initFrom();
      }else{
        alert("请完善线路信息");
      }

    },
    /** 初始化启反程数据列表和数据表单 */
    initFrom(){
      this.$store.commit("roadInfo/clearRoadStation");
      this.form.roadNo = '';
      this.form.cost = '';
      this.form.time = '';
      this.form.cityId = '';
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
      this.$store.commit('roadInfo/getResource');
    },
    /**
     * 生成反向路线
     * */
    createReturn(){
      this.$store.commit('roadInfo/createReturn');
      this.$store.commit('roadInfo/getResource');
    },
    /**
     * 点击删除站点
     * */
    deleteStation(stationIndex){
      this.$store.commit('roadInfo/deleteStation', stationIndex);
      this.$store.commit('roadInfo/getResource');
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
        this.$store.commit('roadInfo/getResource');
      }else if (this.openMapType == 1){
        this.$store.commit('roadInfo/addFirstStation');
        this.$store.commit('roadInfo/getResource');
      }else if (this.openMapType == 2){
        this.$store.commit('roadInfo/addStation',this.stationIndex);
        this.$store.commit('roadInfo/getResource');
      }else if (this.openMapType==3){
        this.$store.commit('roadInfo/addReturn', this.stationIndex);
        this.$store.commit('roadInfo/getResource');
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
