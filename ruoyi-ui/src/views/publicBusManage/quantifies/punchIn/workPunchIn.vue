<template>
  <div>
    <el-container>
      <el-header><font color="#ff4500"><h2>{{words}}</h2></font></el-header>
      <el-main style="height: 530px ">
        <div>
          <font color="white"><h2 style="position: relative;left: 50px">{{ date }}</h2></font>
        </div>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item prop="userName">
            <el-input style="width: 220px" placeholder="用户名" v-model="form.userName" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input style="width: 220px" placeholder="密码" type="password" v-model="form.password" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">确认打卡</el-button>
            <el-button>返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import {
  listQuantifies,
  getQuantifies,
  delQuantifies,
  addQuantifies,
  updateQuantifies,
  exportQuantifies,
  updateWorkPunchIn
} from "@/api/publicBusManage/quantifies/quantifies";

export default {
  components: {
    listQuantifies,
    getQuantifies,
    delQuantifies,
    addQuantifies,
    updateQuantifies,
    exportQuantifies,
    updateWorkPunchIn
  },
  name: "workPunchIn",
  data() {
    return {
      words:'待打卡...',
      //第二新增时间显示
      nowYear: '',
      nowMonth: '',
      nowDate: '',
      nowWeek: '',
      //第一时间显示
     date: new Date(),
      //参数
      queryParams: {
        userName:null,
        password:null,
        pageNum: 1,
        pageSize: 10,
        userId: null,
        busdriverid: null,
        driverworktime: null,
        atworkpunch: new Date(),
        offdutypunch: null,
        gobuspunch: null,
        sysUserId: null,
        tDriverId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  methods: {
    onSubmit() {
      //给参数赋值
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userName != null&&this.form.password!=null) {
            updateWorkPunchIn(this.form).then(response => {
              if(response==1){
                this.msgSuccess("打卡成功！");
                this.words='已打卡...'
              }else if(response==0){
                this.msgError("打卡失败！")
              }else if(response==2){
                this.msgError("未到打卡时间！")
              }
            });
          } else {
            this.msgError("信息不能为空！")
          }
        }
      });
    },

  //  第二新增时间显示
    getdate() {
      const year = new Date().getFullYear()
      const month = new Date().getMonth() + 1 < 10 ? '0' + (new Date().getMonth() + 1) : (new Date().getMonth() + 1)
      const date = new Date().getDate() < 10 ? '0' + new Date().getDate() : new Date().getDate()
      const week = new Date().getDay()
      if (week === 1) {
        this.nowWeek = '星期一'
      } else if (week === 2) {
        this.nowWeek = '星期二'
      } else if (week === 3) {
        this.nowWeek = '星期三'
      } else if (week === 4) {
        this.nowWeek = '星期四'
      } else if (week === 5) {
        this.nowWeek = '星期五'
      } else if (week === 6) {
        this.nowWeek = '星期六'
      } else if (week === 27) {
        this.nowWeek = '星期日'
      }
      this.nowYear = year
      this.nowMonth = month
      this.nowDate = date
    }
  },

  mounted() {
    //第一时间显示
    let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
    this.timer = setInterval(() => {
      _this.date = new Date(); // 修改数据date
    }, 1000)
  //  第二时间显示
    this.getdate()
  },
  // 销毁定时器
  beforeDestroy: function () {
    //第一时间显示
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: lightseagreen;
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
  background-color: lightpink;
  color: #333;
  text-align: center;
  line-height: 160px;
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
