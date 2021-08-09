<template>
  <div id="viewSchedule">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
                <span v-if="oneRoadInfo!==null">线路编号：{{ oneRoadInfo.busNo }}</span>
      </div>
      <div  class="text item">
        <el-collapse>
          <div v-for="time in oneSchedule">
            <el-collapse-item :title="time.starTime + ':00--'+time.endTime+':00'">
              <div ><span v-for="de in getSpecificTime(time.starTime,time.endTime,time.timeInterval)">{{ de }}，</span></div>
            </el-collapse-item>
          </div>
        </el-collapse>
      </div>
    </el-card>

  </div>
</template>

<script>
import {createNamespacedHelpers} from 'vuex'
const {mapState, mapActions} = createNamespacedHelpers('roadInfo')

export default {
  name: "viewSchedule",
  data(){
    return{

    }
  },
  computed: {
    ...mapState({
      oneRoadInfo: state => state.oneRoadInfo,
      oneSchedule: state => state.oneSchedule
    })
  },
  methods: {
    //获取具体时间
    getSpecificTime(start, end, interval) {
      let h = start
      let m = 0
      const arr = []
      let s = (end - start) * 60 / interval
      if (parseInt(s) === s) {
        s += 1
      } else {
        s = Math.ceil(s)
      }
      for (let i = 0; i < s; i++) {
        if (i === 0) {
          arr[i] = `${h}:00`
        } else {
          m = Number(m) + interval
          if (m > 60) {
            h++
            m -= 60
          } else if (m === 60) {
            h++
            m = '00'
          }
          arr[i] = `${h}:${m}`
        }
      }
      return arr;
    }
  }
}
</script>


<style>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
