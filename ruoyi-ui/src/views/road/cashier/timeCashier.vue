<template>
  <div>
    <div id="timeCashier" :style="{width: '1100px', height: '550px'}"></div>
  </div>
</template>

<script>
export default {
  name: "timeCashier",
  data() {
    return {
      timeCashierData:
        {
          title: {text: '线路收银/每小时'},
          tooltip: {},
          xAxis: {
            data: ['6:00--7:00', '7:00--8:00', '8:00--9:00', '9:00--10:00', '10:00--11:00', '11:00--12:00']
          },
          yAxis: {},
          series: [{
            name: '线路收银/每小时',
            type: 'bar',
            data: [7,32,45,14,17,13]
          }]
        }
    }
  },
  mounted() {
    this.showTime();
  },
  methods: {
    showTime() {
// 基于准备好的dom，初始化echarts实例
      let timeCashier = this.$echarts.init(document.getElementById('timeCashier'), 'dark')
      // 绘制图表
      timeCashier.setOption(this.timeCashierData);
    }
  }
}
</script>
