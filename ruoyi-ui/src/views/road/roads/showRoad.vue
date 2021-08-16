<template>
  <div class="showRoad">
    <div class="bmap-page-container">
      <el-bmap vid="bmapDemo2" :zoom="zoom" :center="center" class="bmap-demo">
        <el-bmapv-view>
          <el-bmapv-simple-line-layer :color="color" :blend="blend" :data="mapRoadSort"></el-bmapv-simple-line-layer>
        </el-bmapv-view>
        <el-bmapv-view>
          <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="mapStationSort" ></el-bmapv-icon-layer>
        </el-bmapv-view>
        <el-bmapv-view>
          <el-bmapv-text-layer color="#FF0000" :font-weight="535" :offset="[0,20]" :font-size="18"
                               :data="mapStationSort"></el-bmapv-text-layer>
        </el-bmapv-view>
      </el-bmap>
    </div>
  </div>
</template>

<style>
.bmap-demo {
  height: 400px;
}
</style>

<script>
import {createNamespacedHelpers} from 'vuex'

const {mapState, mapActions} = createNamespacedHelpers('roadInfo')
export default {
  name: 'showRoad',
  data() {
    return {
      width:32,
      height:32,
      icon: "/local.png",
      count: 1,
      zoom: 14,
      center: [118.14528, 24.49081],
      color: 'rgba(50, 50, 200, 1)',
      blend: 'lighter'
    };
  },
  computed: {
    ...mapState({
      stationSort: state => state.stationSort,
      returnSort: state => state.returnSort,
      mapRoadSort: state => state.mapRoadSort,
      mapStationSort: state => state.mapStationSort
    }),
  },
  mounted() {
  },
  methods: {

  }
};
</script>
