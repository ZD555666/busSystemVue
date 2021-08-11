<template>
  <div>
    <div id="ridership" :style="{width: '1625px', height: '636px'}"></div>
  </div>
</template>

<script>
export default {
  name: "ridership",
  data() {
    return {
      ridershipData:
        {
          title: {text: '站点客流量/天'},
          tooltip: {},
          xAxis: {
            data: ['厦大白城', '胡里山', '厦大艺术学院', '名仕', '珍珠湾', '科技中学','仓里','火车站(湖东)','双涵路口','莲坂北','莲花路口东','宝龙中心']
          },
          yAxis: {},
          series: [{
            name: '站点客流量/天',
            type: 'bar',
            data: [160,256,278,248,245,138,326,470,459,156,325,324]
          }]
        }
    }
  },
  mounted() {
    this.ridership();
  },
  methods: {
    ridership() {
// 基于准备好的dom，初始化echarts实例
      let ridership = this.$echarts.init(document.getElementById('ridership'), 'dark')
      // 绘制图表
      ridership.setOption(this.ridershipData);
    }
  }
}
</script>
