<template>
  <div class="getLocation">
    <el-form>
      <el-row :gutter="24">
        <el-col :span="24">
          <el-col :span="6">
            <el-form-item label="位置经度" prop="lng">
              <el-input
                v-model="model.lng"
                type="number"
                class="input_number"
                @mousewheel.native.prevent
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="位置维度" prop="lat">
              <el-input
                v-model="model.lat"
                type="number"
                class="input_number"
                @mousewheel.native.prevent
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <baidu-map
              class="bm-view"
              :center="center"
              :zoom="zoom"
              :scroll-wheel-zoom="true"
              @ready="createMap"
            >
              <bm-bus keyword="92" :auto-viewport="true" location="厦门"></bm-bus>
              <!--            搜索-->
              <bm-local-search :keyword="model.address" :auto-viewport="true"
                               style="display: none"></bm-local-search>
              <!--            标记-->
              <bm-marker-clusterer>
                <bm-marker :position="{lng: model.lng, lat: model.lat}"/>
              </bm-marker-clusterer>
              <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true"
                              :autoLocation="true"></bm-geolocation>
            </baidu-map>
            <el-input v-model="model.address" placeholder="搜索地点"
                      style="margin-left: 10px;width: 200px;position: absolute;top: 25%;opacity: 0.9"
                      prefix-icon="el-icon-search"></el-input>
          </el-col>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>

export default {
  data() {
    return {
      center: {lng: 0, lat: 0},
      zoom: 10,
      model: {
        lng: '',
        lat: '',
        address: ''
      }
    }
  },
  methods: {
    createMap({BMap, map}) {
      // 百度地图API功能
      this.center.lng = 118.127529
      this.center.lat = 24.547094
      this.zoom = this.zoom
      this.map2 = map
      const viewthis = this
      map.addEventListener('click', (e) =>{
        viewthis.model.lng = e.point.lng
        viewthis.model.lat = e.point.lat
        // console.log("子页面",this.$refs.child1)
      })

      // 区域图
      var datas = new Array('徐州市-#665599')
      var bdary = new BMap.Boundary()
      for (var i = 0; i < datas.length; i++) {
        getBoundary(datas[i], bdary)
      }

      // 设置区域图
      function getBoundary(data, bdary) {
        data = data.split('-')
        bdary.get(data[0], function (rs) {
          // 获取行政区域
          var count = rs.boundaries.length // 行政区域的点有多少个
          // var pointArray = []
          for (var i = 0; i < count; i++) {
            var ply = new BMap.Polygon(rs.boundaries[i], {
              strokeWeight: 2,
              strokeColor: '#ff0000',
              fillOpacity: 0.1,
              fillColor: data[1]
            }) // 建立多边形覆盖物
            map.addOverlay(ply) // 添加覆盖物
          }
        })
      }
    }
  }
}
</script>
<style scoped>
.bm-view {
  width: 100%;
  height: 450px;
}
</style>

