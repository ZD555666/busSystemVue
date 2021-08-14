<template>
  <div class="cashier">
    <el-row>
      <el-form>
        <el-col :span="7" :push="3">
          <el-form-item label="选择开始时间：" label-width="120px" style="margin: 20px">
              <el-date-picker
                v-model="value1"
                align="right"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions">
              </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="7" :push="2">
          <el-form-item label="选择结束时间：" label-width="120px" style="margin: 20px">
            <el-date-picker
              v-model="value2"
              align="right"
              type="date"
              placeholder="选择日期"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="4" label-width="100px" style="margin: 20px" :push="1">
          <el-form-item label="总金额合计(元)：" label-width="120px">
            <el-input value="13548￥" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-row >
      <el-col :push="3">
        <div id="myChart" :style="{width: '1100px', height: '550px'}"></div>
      </el-col>
    </el-row>
    <el-dialog
      title="提示"
      :visible.sync="openRoadSum"
      width="70%">
      <el-row>
        <span style="font-weight: 500;font-size: 20px">线路：{{ colName }}</span>
      </el-row>
      <el-row>
        <day-cashier ref="child1"></day-cashier>
      </el-row>
    </el-dialog>

  </div>
</template>

<script>
import dayCashier from "@/views/road/cashier/dayCashier";
export default {
  name: 'cashier',
  components:{dayCashier},
  data() {
    return {
      openRoadSum: false,
      option:
        {
        title: {text: '线路收银/月'},
        tooltip: {},
        xAxis: {
          data: ['1路', '2路', '3路', '4路','5路','6路']
        },
        yAxis: {
        },
        series: [{
          name: '线路收银/月',
          type: 'bar',
          data: [ 1520, 2000, 2430, 2200,1258,4260]
        }]
      },
      //日期选择器
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      value2: '',
      colName:''
    }
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'),'white');
      // 绘制图表
      myChart.setOption(this.option);
      myChart.on('click', (params)=>{
        this.colName= params.name;
        console.log("params", this.colName);
        // this.$refs.child1.parentClick();
        this.openRoadSum= true;
      });
    }
  }
}


</script>
