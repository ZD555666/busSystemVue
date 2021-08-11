<template>
  <div>
      <div id="dayCashier" :style="{width: '1100px', height: '550px'}"> </div>
    <el-dialog
      title="提示"
      :visible.sync="openTimeCashier"
      width="70%">
      <el-row>
        <span style="font-size: 20px;font-weight: 500;">日期：{{ colName }}</span>
      </el-row>
      <el-row>
        <time-cashier></time-cashier>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import timeCashier from "@/views/road/cashier/timeCashier";
export default {
  name:"dayCashier",
  components:{timeCashier},
  data(){
    return{
      openTimeCashier:false,
      dayCashierData:
        {
          title: {text: '线路收银/天'},
          tooltip: {},
          xAxis: {
            data: ['2021.8.6', '2021.8.7', '2021.8.8', '2021.8.9', '2021.8.10']
          },
          yAxis: {},
          series: [{
            name: '线路收银/天',
            type: 'bar',
            data: [63, 75, 56, 79, 40]
          }]
        },
      colName:''
    }
  },
  mounted() {
    this.showDay();
  },
  methods:{
    showDay() {
    // 基于准备好的dom，初始化echarts实例
      let dayCashier = this.$echarts.init(document.getElementById('dayCashier'), 'dark')
      // 绘制图表
      dayCashier.setOption(this.dayCashierData);
      dayCashier.on('click', (params) => {
        this.colName = params.name;
        this.openTimeCashier = true;
      });
    }
  }
}
</script>
