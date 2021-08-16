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
          :data="busschedulingList1"
          border
          style="width:80%;">
          <el-table-column
            label="时间"
            prop="startime">
          </el-table-column>
          <el-table-column
            label="发车"
            prop="licenseplate">
          </el-table-column>
          <el-table-column
            label="操作"
            prop="date2">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-on:click="tihuan(scope.row)"
                @click="dialogTableVisible3=false"
              >替换</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="dialogTableVisible2=true"
                v-on:click="paiban(scope.row)"
              >排班</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div></el-col>
      <el-col :span="12"><div>
        <b>返程</b>
        <el-table
          :data="busschedulingList2"
          border
          style="width:80%;">
          <el-table-column
            label="时间"
            prop="startime">
          </el-table-column>
          <el-table-column
            label="发车"
            prop="licenseplate">
          </el-table-column>
          <el-table-column
            label="操作"
            prop="date2">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-on:click="tihuan(scope.row)"
                @click="dialogTableVisible4=false"
              >替换</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="dialogTableVisible=true"
                v-on:click="paiban(scope.row)"
              >排班</el-button>

            </template>
          </el-table-column>
        </el-table>
      </div></el-col>
    </el-row>
    <el-dialog title="终点停靠车辆选择排班" :visible.sync="dialogTableVisible">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-select v-model="form.licenseplate"placeholder="请选择车辆">
          <el-option
            v-for="item in tableData4"
            :key="item.licenseplate"
            :label="item.licenseplate"
            :value="item.licenseplate">
          </el-option>
        </el-select>
        <el-button
          v-on:click="queding()"
        >确定</el-button>
      </el-form>
    </el-dialog>
    <el-dialog title="终点停靠车辆选择替换" :visible.sync="dialogTableVisible3">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-select v-model="form.licenseplate"placeholder="请选择车辆">
          <el-option
            v-for="item in tableData4"
            :key="item.licenseplate"
            :label="item.licenseplate"
            :value="item.licenseplate">
          </el-option>
        </el-select>
        <el-button
          v-on:click="queding2()"
        >确定</el-button>
      </el-form>
    </el-dialog>
    <el-dialog title="起点停靠车辆排班" :visible.sync="dialogTableVisible2">
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-select v-model="form.licenseplate"placeholder="请选择车辆">
          <el-option
            v-for="item in tableData3"
            :key="item.licenseplate"
            :label="item.licenseplate"
            :value="item.licenseplate">
          </el-option>
        </el-select>
        <el-button
          v-on:click="queding()"
        >确定</el-button>
      </el-form>
    </el-dialog>
    <el-dialog title="起点停靠车辆替换" :visible.sync="dialogTableVisible4">
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-select v-model="form.licenseplate"placeholder="请选择车辆">
          <el-option
            v-for="item in tableData3"
            :key="item.licenseplate"
            :label="item.licenseplate"
            :value="item.licenseplate">
          </el-option>
        </el-select>
        <el-button
          v-on:click="queding2()"
        >确定</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import { listBusinfo2,listBusinfo,listBusinfo3,listBusinfo4,listBusinfo5,} from "@/api/busMax/busManage/businfo";
import { listBusscheduling1,listBusscheduling2,listBusscheduling,updateBusscheduling,getBusscheduling,} from "@/api/busMax/busScheduling/busscheduling";
export default {
  name: "busScheduling2",
  created() {
    this.getList();
  },
  reset() {
    this.form = {
      busid: null,
      busno: null,
      licenseplate: null,
      startime: null
    };
    this.resetForm("form");
  },
  methods:{
    paiban(row){
      const busid = row.busid || this.ids
      getBusscheduling(busid).then(response => {
        this.form = response.data;
        console.log(busid)
      });
    },
    tihuan(row){
      const licenseplate = row.licenseplate || this.licenseplate;
      if (licenseplate == null) {
        this.dialogTableVisible=false,
          this.dialogFormVisible=false,
          this.dialogTableVisible2=false,
          this.dialogFormVisible2=false,
          this.dialogTableVisible3=false,
          this.dialogFormVisible3=false,
          this.dialogTableVisible4=false,
          this.dialogFormVisible4=false,
          this.msgSuccess("请先进行此时刻车辆排班");
      }else {
        this.dialogTableVisible4=true
        this.dialogTableVisible3=true
      }
      const busid = row.busid || this.ids
      getBusscheduling(busid).then(response => {
        this.form = response.data;
        console.log(busid)
      });
    },
    queding(){
      this.$confirm('是否确认对此车辆的排班?', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
      }).then(() => {
        this.$refs["form"].validate(valid => {
          updateBusscheduling(this.form).then(response => {
            this.dialogTableVisible=false,
              this.dialogFormVisible=false,
              this.dialogTableVisible2=false,
              this.dialogFormVisible2=false,
              this.getList();
          });

        });
        this.msgSuccess("排班成功");
        this.getList();
      }).catch(() => {
      });
    },
    queding2(){
      this.$confirm('是否确认对此车辆进行替换?', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
      }).then(() => {
        this.$refs["form"].validate(valid => {
          updateBusscheduling(this.form).then(response => {
            this.dialogTableVisible=false,
              this.dialogFormVisible=false,
              this.dialogTableVisible2=false,
              this.dialogFormVisible2=false,
              this.getList();
          });

        });
        this.msgSuccess("替换成功");
        this.getList();
      }).catch(() => {
      });
    },
    busScheduling2() {
      this.$router.push('/busScheduling?' + `licenseplate=${this.$route.query.licenseplate}&busno=${this.$route.query.busno}`)
    },
    getList() {
      this.loading = true;
      listBusscheduling2().then(response => {
        this.busschedulingList2 = response.rows;
        this.total = response.total;
        this.loading = false;
        for (var i=0;i<this.busschedulingList2.length;i++) {
            var licenseplate = this.busschedulingList2[i].licenseplate
            var busno = this.busschedulingList2[i].busno
        }
      });
      this.loading = true;
      listBusscheduling().then(response => {
        this.busschedulingList = response.rows;
        this.total = response.total;
        this.loading = false;

      });
      this.loading = true;
      listBusscheduling1().then(response => {
        this.busschedulingList1 = response.rows;
        this.total = response.total;
        this.loading = false;

      });
      //起点车辆
      listBusinfo3().then(response => {
        this.tableData3 = response.rows;
        this.total = response.total;
        for (var i=0;i<this.tableData3.length;i++) {
            var licenseplate = this.tableData3[i].licenseplate
            var busparam = this.tableData3[i].busparam
            var busState = this.tableData3[i].busState
            this.licenseplate1=licenseplate
        }
        this.loading = true;
      });
      //终点车辆
      listBusinfo4().then(response => {
        this.tableData4 = response.rows;
        this.total = response.total;
        for (var i=0;i<this.tableData4.length;i++) {
            var licenseplate = this.tableData4[i].licenseplate
            var busparam = this.tableData4[i].busparam
            var busState = this.tableData4[i].busState
            this.licenseplate1=licenseplate
        }
        this.loading = true;
      });
      //故障车辆
      listBusinfo5().then(response => {
        this.tableData5 = response.rows;
        this.total = response.total;
        for (var i=0;i<this.tableData5.length;i++) {
            var licenseplate = this.tableData5[i].licenseplate
            var busparam = this.tableData5[i].busparam
            var busState = this.tableData5[i].busState
            this.licenseplate1=licenseplate
        }
        this.loading = true;
      });
    }
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // busscheduling表格数据
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busno: null,
        licenseplate: null,
        startime: null
      },
      // 表单参数

      // 表单校验
      rules: {
      },
      busschedulingList1:[],
      busschedulingList2:[],
      busschedulingList:[],
      dialogTableVisible: false,
      dialogFormVisible: false,
      dialogTableVisible2: false,
      dialogFormVisible2: false,
      dialogTableVisible3: false,
      dialogFormVisible3: false,
      dialogTableVisible4: false,
      dialogFormVisible4: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
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
