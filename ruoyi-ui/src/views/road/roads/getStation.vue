<template>
  <div class="getStation">
    <div class="bmap-page-container">
      <el-bmap vid="bmapDemo1" :zoom="zoom" :center="center" class="bmap-demo">
        <el-bmapv-view>
          <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="stationList"
                               :enable-picked="true" :on-click="(e)=>{clickMarker(e)}" :auto-select="true">
          </el-bmapv-icon-layer>
          <el-bmapv-text-layer color="#FF0000" :font-weight="535" :offset="[0,40]" :font-size="20"
                               :data="stationList">
          </el-bmapv-text-layer>
        </el-bmapv-view>
      </el-bmap>
    </div>
  </div>
</template>

<style>
.bmap-demo {
  height: 535px;
}
</style>

<script>
import {listStationinfo, stationList} from "@/api/road/stationinfo";

module.exports = {
  name: 'getStation',
  data() {
    return {
      stationList: [],
      count: 1,
      zoom: 14,
      center: [118.14528, 24.49081],
      width: 32,
      height: 32,
      icon: "/local.png",
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
      // console.log(e.dataIndex);
      console.log("鼠标点击的站点",e);

      if (e.dataIndex>=0){
        this.$store.commit('roadInfo/stationSelect', e);
      }else{
        alert("选择错误，请选择已有的站点");
      }
    },
    getStationList() {
      stationList().then(response => {
        var stations = [];
        var responses = [];
        responses = response;
        console.log("查询到的数据库站点信息",responses);
        for (let i = 0; i < responses.length; i++) {
          var station =
            {
              geometry: {
                type: 'Point',
                coordinates: [responses[i].xpoint, responses[i].ypoint],
              },
              properties: {
                // icon: '/local.png',
                text: responses[i].stationname,
                stationId: responses[i].stationid
              }
            }
          stations.push(station);
        }
        this.stationList = stations;
        console.log("地图生成的站点信息", this.stationList);
      });
    }
  }
};
</script>
