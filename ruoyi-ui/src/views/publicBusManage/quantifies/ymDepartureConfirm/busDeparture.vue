<template>
  <el-container>
    <el-header><h2>出站确认</h2></el-header>
    <el-main style="height: 530px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item prop="busparam">
          <el-select v-model="form.busparam" placeholder="选择车辆状态">
            <el-option
              v-for="item in cities"
              :key="item.value"
              :label="item.label"
              :value="item.value">
              <span style="float: left">{{ item.label }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
            </el-option>
          </el-select>
        </el-form-item >
        <el-form-item prop="licenseplate">
          <el-autocomplete
            style="width: 220px"
            v-model="form.licenseplate"
            :fetch-suggestions="querySearchAsync"
            placeholder="发车车牌"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>

        <el-form-item prop="busno">
          <el-autocomplete
            style="width: 220px"
            v-model="form.busno"
            :fetch-suggestions="querySearchAsyncLine"
            placeholder="公交线路"
          ></el-autocomplete>
        </el-form-item>

        <el-form-item prop="driverName">
          <el-input
            style="width: 220px"
            placeholder="司机姓名"
            v-model="form.driverName"
            clearable>
          </el-input>
        </el-form-item>

        <el-form-item prop="goBusTime">
          <el-time-picker
            v-model="form.goBusTime"
            :picker-options="{
      selectableRange: '00:00:00 - 23:59:00'
    }"
            placeholder="任意时间点">
          </el-time-picker>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
import { listDepartureConfirm, getDepartureConfirm, delDepartureConfirm, addDepartureConfirm, updateDepartureConfirm, exportDepartureConfirm ,addGoBusConfirm} from "@/api/publicBusManage/departureConfirm/departureConfirm";
export default {
  components:{
    listDepartureConfirm,
    getDepartureConfirm,
    delDepartureConfirm,
    addDepartureConfirm,
    updateDepartureConfirm,
    exportDepartureConfirm,
    addGoBusConfirm
  },
  name: "busDeparture",
  data() {
    return {
      // 遮罩层
      loading: true,
      //发车时间
      value1: new Date( ),
        //司机姓名
      input: '',
        //选择器
        cities: [{
          value: '1',
          label: '行驶'
        }, {
          value: '2',
          label: '起点停靠'
        },{
          value: '3',
          label: '终点停靠'
        }],
        value: '',
      //表单
      form: {},
      // 表单校验
      rules: {
      },
    //  车牌远程搜索框
      restaurants: [],
      state: '',
      timeout:  null,
      //  线路远程搜索框
      restaurantsLine: [],
      stateLine: '',
      timeoutLine:  null,
      //请求参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        busno: null,
        licenseplate: null,
        busparam: null,
        roadid: null,
        driverName:null,
        goBusTime:null,
        attr2: null
      },
    //  公交信息列表
      departureConfirmList: [],
      licenseplatelist:[]
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },

    //远程搜索框
    // 车牌
    querySearchAsync(queryString, cb) {
     var list=[{}];
      listDepartureConfirm(this.queryParams).then(response => {
        for (let i of response.rows) {
          i.value=i.licenseplate
        }
        list=response.rows
      });
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(list);
      }, 3000 * Math.random());
    },
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    },

    //公交线路
    querySearchAsyncLine(queryString, cb) {
      var list=[{}];
      listDepartureConfirm(this.queryParams).then(response => {
        for (let i of response.rows) {
          i.value=i.busno
        }
        list=response.rows
      });
      clearTimeout(this.timeoutLine);
      this.timeoutLine = setTimeout(() => {
        cb(list);
      }, 3000 * Math.random());
    },

  //  提交按钮
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.licenseplate != null) {
            addGoBusConfirm(this.form).then(response => {
              alert(response)
              if(response==1){
                this.msgSuccess("出站确认成功！");
              }else if (response==0){
                this.msgError("司机名不正确")
              }
            });
          } else {
            this.msgError("确认失败！")
          }
        }
      });
    },

  //  取消按钮方法
    cancel(){

    }

  },
  //钩子函数
  mounted() {

  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: cadetblue;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: lightgray;
  color: #333;
  text-align: center;
  line-height: 200px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
