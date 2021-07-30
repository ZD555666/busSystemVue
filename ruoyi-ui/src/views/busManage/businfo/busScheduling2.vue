<template>
  <div>
    <h1>{{this.$route.query.licenseplate}}车辆排班</h1>
    <el-row>
      <el-col :span="2"><div class="grid-content bg-purple">6:00~10:00<br>高峰期</div></el-col>
      <el-col :span="10"><div class="grid-content bg-purple-light">10:00~16:00<br>正常客流量</div></el-col>
      <el-col :span="2"><div class="grid-content bg-purple">16:00~20:00<br>高峰期</div></el-col>
      <el-col :span="10"><div class="grid-content bg-purple-light">20:00~22:00<br>正常客流量</div></el-col>
    </el-row>
    <br>
    <el-row>
      <el-col :span="6"><div class="grid-content ">
        <i class="el-icon-top"></i><br>
      </div></el-col>
      <el-col :span="6"  ><div class="grid-content ">
        <i class="el-icon-top"><br></i>
      </div></el-col>
      <el-col :span="6"><div class="grid-content">
        <i class="el-icon-top"><br></i>
      </div></el-col>
      <el-col :span="6"  ><div class="grid-content ">
        <i class="el-icon-top"><br></i>
      </div></el-col>
    </el-row>
    <br>
    <el-row>
      <el-col :span="6"><div>{{this.$route.query.date}}高峰期运行</div></el-col>
      <el-col :span="6"><div>可排班时段</div></el-col>
      <el-col :span="6"><div>{{this.$route.query.date}}高峰期运行</div></el-col>
      <el-col :span="6"><div>可排班时段</div></el-col>
    </el-row>
    <br><br>
    <el-button  @click.native="busScheduling2()">
      <router-link to="/busScheduling">重选班次</router-link>
    </el-button>
    <br><br>
    <el-row>
      <el-col :span="12"><div>
        <b>始程</b>
        <el-table
          :data="tableData"
          border
          style="width:80%;">
          <el-table-column
            label="序号"
            prop="busid"
           >
          </el-table-column>
          <el-table-column
            label="时间"
            prop="date">
          </el-table-column>
          <el-table-column
            label="发车"
            prop="ss">
          </el-table-column>
          <el-table-column
            label="操作"
            prop="date2">
          </el-table-column>
        </el-table>
      </div></el-col>
      <el-col :span="12"><div>
        <b>返程</b>
        <el-table
          :data="tableData"
          border
          style="width:80%;">
          <el-table-column
            label="序号"
            prop="date">
          </el-table-column>
          <el-table-column
            label="时间"
            prop="date">
          </el-table-column>
          <el-table-column
            label="发车"
            prop="date">
          </el-table-column>
          <el-table-column
            label="操作"
            prop="date2">

          </el-table-column>
        </el-table>
      </div></el-col>
    </el-row>
  </div>
</template>

<script>


export default {
  name: "busScheduling2",
  methods:{
    busScheduling2() {
      this.$router.push('/busScheduling?' + `licenseplate=${this.$route.query.licenseplate}&busno=${this.$route.query.busno}`)
      // this.$router.push({
      //   path:'/busScheduling',
      //   query:{
      //     licenseplate:this.$route.query.licenseplate,
      //     busno:this.$route.query.busno
      //   }
      // })
      console.log(this.$route.query.licenseplate)
      console.log(this.$route.query.busno)
      console.log(this.$route)
    }
  },
  data() {
    return {
  tableData:[{
    date2:
      <el-button>排班</el-button>,

  }]
    }
  },

}
</script>

<style scoped>
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  font-size: 16px;
  text-align: center;
  border-radius: 20px;
  min-height: 36px;
}

i{
  font-size: 50px;
  margin-right: 68%;
}
</style>
