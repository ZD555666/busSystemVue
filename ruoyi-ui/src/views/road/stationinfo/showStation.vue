<template>
  <div class="showStation">
    <div class="bmap-page-container">
      <el-bmap vid="bmapDemo3" :zoom="zoom" :center="center" class="bmap-demo">
        <el-bmapv-view>
          <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="stationList"
                               :enable-picked="true"></el-bmapv-icon-layer>
          <el-bmapv-text-layer color="#FF0000" :stroke-color="'red'" :font-weight="600" :offset="[0,40]" :font-size="20"
                               :data="stationList" :enable-picked="true" :auto-select="true"
                               :on-click="(e)=>{clickMarker(e)}"></el-bmapv-text-layer>
        </el-bmapv-view>
      </el-bmap>
    </div>
  </div>
</template>

<style>
.bmap-demo {
  height: 550px;
}
</style>

<script>
import {listStationinfo, stationList} from "@/api/road/stationinfo";

export default{
  name: 'showStation',
  data() {
    return {
      stationList:[],
      count: 1,
      zoom: 14,
      center: [118.14528, 24.49081],
      width: 24,
      height: 40,
      icon: './assets/images/layer/position1.png',
      data: [],
      visible: true
    };
  },
  created() {
    this.getStationList();
  },
  mounted() {
  },
  methods: {
    clickMarker(e) {
      console.log(e.dataIndex);
      // console.log(e.dataItem.geometry.coordinates[0]);
    },
    getStationList(){
      stationList().then(response => {
        var stations=[];
        var responses = [];
        responses= response;
        console.log("数据库回调站点信息==============================================");
        console.log(responses);
        for (let i = 0; i < responses.length; i++) {
          var station =
            {
              geometry: {
                type: 'Point',
                coordinates: [responses[i].xpoint, responses[i].ypoint],
              },
              properties: {
                icon: './assets/images/layer/position1.png',
                text: responses[i].stationname
              }
            }
          stations.push(station);
        }
        this.stationList = stations;
        console.log("站点信息==============================================");
        console.log(this.stationList);
      });
    }
  }
};
</script>
