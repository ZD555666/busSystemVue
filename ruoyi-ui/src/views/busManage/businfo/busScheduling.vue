<template>
  <div>
    <h1>{{this.$route.query.licenseplate}}车辆排班</h1>
    <el-row>
      <el-col :span="2"><div class="grid-content bg-purple">7:00~10:00<br>高峰期</div></el-col>
      <el-col :span="10"><div class="grid-content bg-purple-light">10:00~16:00<br>正常客流量</div></el-col>
      <el-col :span="2"><div class="grid-content bg-purple">16:00~19:00<br>高峰期</div></el-col>
      <el-col :span="10"><div class="grid-content bg-purple-light">19:00~23:00<br>正常客流量</div></el-col>
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
      <el-col :span="6"><div>{{this.$route.query.busno}}高峰期运行</div></el-col>
      <el-col :span="6"><div>可排班时段</div></el-col>
      <el-col :span="6"><div>{{this.$route.query.busno}}高峰期运行</div></el-col>
      <el-col :span="6"><div>可排班时段</div></el-col>
    </el-row>
    <br><br>
    <b>可选班次</b>
    <el-table
              :data="tableData"
              style="width: 30%">
      <el-table-column

        fixed
        label="班次"
        prop="busno"
      >
      </el-table-column>
      <el-table-column
        label="操作"
      >
        <template slot-scope="scope">
          <el-button @click="busScheduling(scope.row)"type="text" size="small">选择此班次</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listBusinfo1} from "@/api/busMax/busManage/businfo";
import busScheduling from "@/views/busManage/businfo/busScheduling";
export default {
  name: "busScheduling",
  created() {
    this.getList();
  },
  methods:{

    busScheduling(row) {
      const busno = row.busno || this.busno;
      console.log(row.busno)
      this.$router.push('/busScheduling2?' + `licenseplate=${this.$route.query.licenseplate}&busno=${this.$route.query.busno}&date=${row.busno}`)
      console.log(this.$route.query.licenseplate)
      console.log(this.$route.query.busno)
      console.log(this.$route)
    },
    getList() {
      listBusinfo1().then(response => {
        this.tableData = response.rows;
        this.total = response.total;
        for (var i=0;i<this.tableData.length;i++){
          this.bus= this.tableData[i].busno

          console.log(this.bus)
        }
        this.loading = true;

      });
    }

  },

  data() {
    return {
      tableData:[]
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
