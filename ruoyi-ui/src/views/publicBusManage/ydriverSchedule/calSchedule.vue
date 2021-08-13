<!--<template>-->
<!--  <div>-->
<!--  &lt;!&ndash;    <full-calendar&ndash;&gt;-->
<!--  &lt;!&ndash;      ref="calendar"&ndash;&gt;-->
<!--  &lt;!&ndash;      :events="events"&ndash;&gt;-->
<!--  &lt;!&ndash;      :config="config"&ndash;&gt;-->
<!--  &lt;!&ndash;      :date="date"&ndash;&gt;-->
<!--  &lt;!&ndash;      locale="fr"&ndash;&gt;-->
<!--  &lt;!&ndash;      @event-selected='eventClick'&ndash;&gt;-->
<!--  &lt;!&ndash;      @day-click="dayClick">&ndash;&gt;-->
<!--  &lt;!&ndash;    </full-calendar>&ndash;&gt;-->
<!--&lt;!&ndash;  日历&ndash;&gt;-->

<!--  <full-calendar-->
<!--    ref="calendar"-->
<!--    :events="events"-->
<!--    :config="config"-->
<!--    @event-selected='eventClick'-->
<!--    @day-click="dayClick">-->
<!--  </full-calendar>-->

<!--&lt;!&ndash;    <adddriver-schedule v-if="isAdd" :isAdd='isAdd' :editItem='editItem' @add='addItem' @close='isAdd = false'></adddriver-schedule>&ndash;&gt;-->
<!--  <el-dialog :title="title" :visible.sync="DialogVisible" width="640px" style="height: 600px" center>-->
<!--    <el-form ref="form" :model="form" label-width="80px">-->
<!--&lt;!&ndash;发车时间&ndash;&gt;-->
<!--      <el-form-item label="发车时刻">-->
<!--        <el-autocomplete-->
<!--          v-model="stateTi"-->
<!--          :fetch-suggestions="querySearchAsyncTi"-->
<!--          placeholder="请输入发车时刻"-->
<!--          @select="handleSelectTi"-->
<!--          style="width: 300px;height: 50px"-->
<!--        ></el-autocomplete>-->
<!--      </el-form-item>-->
<!--&lt;!&ndash;公交线路&ndash;&gt;-->
<!--      <el-form-item label="公交线路">-->
<!--        <el-autocomplete-->
<!--          v-model="stateLi"-->
<!--          :fetch-suggestions="querySearchAsyncLi"-->
<!--          placeholder="请输入公交线路"-->
<!--          @select="handleSelectLi"-->
<!--          style="width: 300px;height: 50px"-->
<!--        ></el-autocomplete>-->
<!--      </el-form-item>-->
<!--&lt;!&ndash;值班司机&ndash;&gt;-->
<!--      <el-form-item label="值班司机">-->
<!--        <el-autocomplete-->
<!--          v-model="stateDr"-->
<!--          :fetch-suggestions="querySearchAsyncDr"-->
<!--          placeholder="请输入司机姓名"-->
<!--          @select="handleSelectDr"-->
<!--          style="width: 300px;height: 50px"-->
<!--        ></el-autocomplete>-->
<!--      </el-form-item>-->
<!--&lt;!&ndash;值班站点&ndash;&gt;-->
<!--      <el-form-item label="值班站点">-->
<!--        <el-autocomplete-->
<!--          v-model="stateSt"-->
<!--          :fetch-suggestions="querySearchAsyncSt"-->
<!--          placeholder="请输入值班站点"-->
<!--          @select="handleSelectSt"-->
<!--          style="width: 300px;height: 50px"-->
<!--        ></el-autocomplete>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancelForm">取 消</el-button>-->
<!--  </span>-->
<!--  </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { listdriverSchedule, getdriverSchedule, deldriverSchedule, adddriverSchedule, updatedriverSchedule, exportdriverSchedule ,selectBusSchedule,selectStationInfolist} from "@/api/publicBusManage/driverSchedule/driverSchedule";-->
<!--import axios from "axios";-->
<!--export default {-->
<!--  name: "calSchedule",-->
<!--  components:{-->
<!--    listdriverSchedule,-->
<!--    getdriverSchedule,-->
<!--    deldriverSchedule,-->
<!--    adddriverSchedule,-->
<!--    updatedriverSchedule,-->
<!--    exportdriverSchedule ,-->
<!--    selectBusSchedule,-->
<!--    selectStationInfolist-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      //日历参数-->
<!--      isAdd:false,-->
<!--      //新增参数-->
<!--      title:"司机排班",-->
<!--      DialogVisible: false,-->
<!--      //发车时刻参数-->
<!--      busSchedule:{},-->
<!--      //站点参数-->
<!--      stationInfo:{},-->
<!--      //司机参数-->
<!--      queryParam: {-->
<!--        pageNum: 1,-->
<!--        pageSize: 10,-->
<!--        workshift: null,-->
<!--        workstation: null,-->
<!--        busno: null,-->
<!--        userId: null,-->
<!--        attr1: null,-->
<!--        attr2: null,-->
<!--        attr3: null-->
<!--      },-->
<!--      //排班信息-->
<!--      busScheduleList:[],-->
<!--      busStopList:[],-->
<!--      busdriverList:[],-->
<!--      events: [{-->
<!--        id: 1,-->
<!--        title: '事件内容',-->
<!--        start: '2021-05-20',-->
<!--        end: '2021-5-20'-->
<!--      }, {-->
<!--        id: 2,-->
<!--        title: '春游',-->
<!--        start: '2021-06-20',-->
<!--        end: '2021-06-20'-->
<!--      }],-->
<!--      config: {-->
<!--        firstDay: '0',-->
<!--        locale: 'zh-cn',-->
<!--        defaultView: 'month',-->
<!--        height: 'auto',-->
<!--        fixdWeekCount: false,-->
<!--        allDaySlot: true,-->
<!--        allDayText: '备注',-->
<!--        weekends: true,-->
<!--        handleWindowResize: true,-->
<!--        agendaDay: true,-->
<!--        buttonText: {-->
<!--          prev: '‹',-->
<!--          next: '›',-->
<!--          prevYear: '«',-->
<!--          nextYear: '»',-->
<!--          today: '今天',-->
<!--          month: '月',-->
<!--          week: '周',-->
<!--          day: '天'-->
<!--        },-->
<!--        header: {-->
<!--          left: 'prevYear,prev,today,next,nextYear',-->
<!--        },-->
<!--        selectable: true,-->
<!--        eventRender: this.eventRender,-->
<!--      },-->
<!--      optState: true,-->
<!--      //添加集合-->
<!--      newItem:{},-->
<!--      editItem:{},-->
<!--      //表单-->
<!--      form: {-->
<!--        name: '',-->
<!--        region: '',-->
<!--        date1: '',-->
<!--        date2: '',-->
<!--        delivery: false,-->
<!--        type: [],-->
<!--        resource: '',-->
<!--        desc: ''-->
<!--      },-->
<!--      //排班输入框-->
<!--      //排班时间-->
<!--      restaurantsTi: [],-->
<!--      stateTi: '',-->
<!--      timeoutTi:  null,-->
<!--      //公交线路-->
<!--      restaurantsLi: [],-->
<!--      stateLi: '',-->
<!--      timeoutLi:  null,-->
<!--      //司机姓名-->
<!--      restaurantsDr: [],-->
<!--      stateDr: '',-->
<!--      timeoutDr:  null,-->
<!--      //值班站点-->
<!--      restaurantsSt: [],-->
<!--      stateSt: '',-->
<!--      timeoutSt:  null-->
<!--    }-->
<!--    },-->

<!--  methods: {-->
<!--    //日历新内容-->
<!--    eventRender:function (event, element) {-->
<!--      element.find('.fc-title').attr('title',event.title)-->
<!--    },-->

<!--    //events的点击事件-->
<!--    eventClick(event, jsEvent, pos){-->
<!--      // this.editItem = event-->
<!--      // this.isAdd = true-->
<!--    alert("ddd")-->
<!--    },-->
<!--    //日期事件-->
<!--    dayClick(date, jsEvent, view){ //日期的点击事件-->
<!--      this.DialogVisible=true-->
<!--      // this.editItem = {}-->
<!--      //被选项-->
<!--      // this.isAdd = true-->
<!--      alert("点击"+date)-->


<!--      //请求后端发车时刻信息-->
<!--      selectBusSchedule(this.busSchedule).then(response=>{-->
<!--        this.busScheduleList=response.data-->
<!--      }).catch(error => {-->
<!--        console.log(error)-->
<!--      })-->

<!--      selectStationInfolist(this.stationInfo).then(response=>{-->
<!--        this.busStopList=response.data-->
<!--      }).catch(error=> {-->
<!--        console.log(error)-->
<!--      })-->

<!--      listlicensePlate(this.queryParam).then(response=>{-->
<!--        this.busdriverList=response.rows-->
<!--      }).catch(error=>{-->
<!--        console.log(error)-->
<!--      })-->

<!--      this.events.push({-->
<!--        id:'16',-->
<!--        title: '8:00,我擦无情',-->
<!--        start: '2020-07-01-8:00',-->
<!--        end: '2020-07-01',-->

<!--      })-->

<!--    },-->

<!--    //新增选项-->
<!--    // addItem(detail){-->
<!--    //   this.newItem = JSON.parse(detail)-->
<!--    //   if(this.editItem.id){//如果是编辑，就删掉该条-->
<!--    //     this.events.forEach( (el,ind)=>{-->
<!--    //       if(el.id == this.editItem.id){-->
<!--    //         this.events.splice(ind,1)-->
<!--    //       }-->
<!--    //     })-->
<!--    //   }-->
<!--    //   this.events.push({-->
<!--    //     id : this.editItem.id?this.editItem.id:this.setUuid(),-->
<!--    //     title : this.newItem.title,-->
<!--    //     start : this.newItem.period[0],-->
<!--    //     end : this.newItem.period[1],-->
<!--    //   })-->
<!--    // },-->

<!--    // setUuid(){-->
<!--    //   var s = [];-->
<!--    //   var hexDigits = "0123456789abcdef";-->
<!--    //   for(var i = 0; i < 36; i++){ s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1); }-->
<!--    //   s[14] = "4";-->
<!--    //   s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);-->
<!--    //   s[8] = s[13] = s[18] = s[23];-->
<!--    //   var uuid = s.join("");-->
<!--    //   return uuid;-->
<!--    // },-->
<!--    //提交表单-->
<!--    submitForm(){-->
<!--      this.DialogVisible=false-->

<!--      this.$refs.calendar.fireMethod('prev')-->

<!--    },-->
<!--    //取消提交表单-->
<!--    cancelForm(){-->
<!--      this.DialogVisible=false-->
<!--    },-->

<!--  //  排班输入框-->
<!--  //  发车时间-->

<!--    querySearchAsyncTi(queryString, cb) {-->
<!--      var restaurantsTi = this.restaurantsTi;-->
<!--      var resultsTi = queryString ? restaurantsTi.filter(this.createStateFilterTi) : restaurantsTi;-->
<!--      clearTimeout(this.timeoutTi);-->
<!--      this.timeoutTi = setTimeout(() => {-->
<!--        for(let i of this.busScheduleList){-->
<!--          i.value=i.startTime-->
<!--        }-->
<!--        resultsTi=this.busScheduleList-->
<!--        cb(resultsTi);-->
<!--      }, 3000 * Math.random());-->
<!--    },-->
<!--    createStateFilterTi(queryString) {-->
<!--      return (state) => {-->
<!--        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);-->
<!--      };-->
<!--    },-->
<!--    handleSelectTi(item) {-->
<!--      console.log(item);-->
<!--    },-->

<!--  //  公交线路-->

<!--    querySearchAsyncLi(queryString, cb) {-->
<!--      var restaurantsLi = this.restaurantsLi;-->
<!--      var resultsLi = queryString ? restaurantsLi.filter(this.createStateFilterLi(queryString)) : restaurantsLi;-->

<!--      clearTimeout(this.timeoutLi);-->
<!--      this.timeoutLi = setTimeout(() => {-->
<!--          for (let j of this.busScheduleList){-->
<!--            j.value=j.busno-->
<!--          }-->
<!--        resultsLi=this.busScheduleList-->
<!--        cb(resultsLi);-->
<!--      }, 3000 * Math.random());-->
<!--    },-->
<!--    createStateFilterLi(queryString) {-->
<!--      return (state) => {-->
<!--        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);-->
<!--      };-->
<!--    },-->
<!--    handleSelectLi(item) {-->
<!--      console.log(item);-->
<!--    },-->

<!--  //  司机姓名-->
<!--    querySearchAsyncDr(queryString, cb) {-->
<!--      var restaurantsDr = this.restaurantsDr;-->
<!--      var resultsDr = queryString ? restaurantsDr.filter(this.createStateFilterDr(queryString)) : restaurantsDr;-->
<!--      console.log("queryString"+queryString)-->
<!--      console.log("cb"   +cb)-->
<!--      clearTimeout(this.timeoutDr);-->
<!--      this.timeoutDr = setTimeout(() => {-->
<!--        for (let x of this.busdriverList){-->
<!--          x.value=x.driverName-->
<!--        }-->
<!--        resultsDr=this.busdriverList-->
<!--        cb(resultsDr);-->
<!--      }, 3000 * Math.random());-->
<!--    },-->
<!--    createStateFilterDr(queryString) {-->
<!--      return (state) => {-->
<!--        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);-->
<!--      };-->
<!--    },-->
<!--    handleSelectDr(item) {-->
<!--      console.log(item);-->
<!--    },-->

<!--  //  值班站点-->
<!--    querySearchAsyncSt(queryString, cb) {-->
<!--      var restaurantsSt = this.restaurantsSt;-->
<!--      var resultsSt = queryString ? restaurantsSt.filter(this.createStateFilterSt(queryString)) : restaurantsSt;-->

<!--      clearTimeout(this.timeoutSt);-->
<!--      this.timeoutSt = setTimeout(() => {-->
<!--        for(let y of this.busStopList){-->
<!--          y.value=y.stationName-->
<!--        }-->
<!--        resultsSt=this.busStopList-->
<!--        cb(resultsSt);-->
<!--      }, 3000 * Math.random());-->
<!--    },-->
<!--    createStateFilterSt(queryString) {-->
<!--      return (state) => {-->
<!--        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);-->
<!--      };-->
<!--    },-->
<!--    handleSelectSt(item) {-->
<!--      console.log(item);-->
<!--    },-->

<!--  },-->

<!--  mounted() {-->

<!--  }-->

<!--}-->
<!--</script>-->
<!--<style scoped>-->

<!--</style>-->
