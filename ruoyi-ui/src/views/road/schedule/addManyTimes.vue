<template>
  <div>
    <el-dialog
      title="新增多个时段"
      :visible.sync="dialogVisible"
      width="60%"
      :before-close="handleClose">
      <el-form>
        <el-row>
          <el-col :span="6">
            <div>
              <el-form-item label="公交线路" label-width="100px">
                <el-autocomplete
                  v-model="busNo"
                  :fetch-suggestions="querySearchAsync"
                  placeholder="请输入线路"
                  @select="handleSelect"
                ></el-autocomplete>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <span style="font-weight: 400;font-size: 20px">时段一:</span>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="开始(点钟)" label-width="100px">
                <el-input v-model="formMsg[0].starTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="结束(点钟)" label-width="100px">
                <el-input v-model="formMsg[0].endTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="7">
            <div>
              <el-form-item label="发车间隔(分钟)" label-width="120px">
                <el-input-number v-model="formMsg[0].timeInterval" :min="1" :max="120" ></el-input-number>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <span style="font-weight: 400;font-size: 20px">时段二:</span>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="开始(点钟)" label-width="100px">
                <el-input v-model="formMsg[1].starTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="结束(点钟)" label-width="100px">
                <el-input v-model="formMsg[1].endTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="7">
            <div>
              <el-form-item label="发车间隔(分钟)" label-width="120px">
                <el-input-number v-model="formMsg[1].timeInterval" :min="1" :max="120"></el-input-number>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <span style="font-weight: 400;font-size: 20px">时段三:</span>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="开始(点钟)" label-width="100px">
                <el-input v-model="formMsg[2].starTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="结束(点钟)" label-width="100px">
                <el-input v-model="formMsg[2].endTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="7">
            <div>
              <el-form-item label="发车间隔(分钟)" label-width="120px">
                <el-input-number v-model="formMsg[2].timeInterval" :min="1" :max="120"></el-input-number>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">
            <span style="font-weight: 400;font-size: 20px">时段三:</span>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="开始(点钟)" label-width="100px">
                <el-input v-model="formMsg[3].starTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="结束(点钟)" label-width="100px">
                <el-input v-model="formMsg[3].endTime" :disabled="true">
                </el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="7">
            <div>
              <el-form-item label="发车间隔(分钟)" label-width="120px">
                <el-input-number v-model="formMsg[3].timeInterval" :min="1" :max="120"></el-input-number>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="confirm">确 定</el-button>
      </span>

    </el-dialog>
  </div>
</template>

<script>
import {addManyTimes, getAllBusNo} from "@/api/road/schedule";

export default {
  data() {
    return {
      busNoList: [],
      timeout: null,
      busNo:'',
      formMsg:[
        {
          busNo:'',
          timeInterval: 30,
          starTime: 7,
          endTime: 10
        },
        {
          busNo: '',
          timeInterval: 30,
          starTime: 10,
          endTime: 16
        },
        {
          busNo: '',
          timeInterval: 30,
          starTime: 16,
          endTime: 19
        },
        {
          busNo: '',
          timeInterval: 30,
          starTime: 19,
          endTime: 23
        }
      ],
      dialogVisible: false
    };
  },
  methods: {
    /**
     * 动态查询线路开始
     * */
    loadAllBusNo() {
      let roadList=[];
      getAllBusNo().then(res => {
        for (let i = 0; i < res.length; i++) {
          let road={
            "value": res[i].busNo, "key": res[i].busNo
          }
          roadList.push(road);
        }
        this.busNoList= roadList;
      });
    },
    querySearchAsync(queryString, cb) {
      var busNoList = this.busNoList;
      var results = queryString ? busNoList.filter(this.createStateFilter(queryString)) : busNoList;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 900 * Math.random());
    },
    createStateFilter(queryString) {
      return (busNo) => {
        return (busNo.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log("选择线路", item);
    },
    /**
     * 动态查询线路结束
     * */
    confirm(){
      if (this.busNo!=''){
        let param = {
          busNo: this.busNo,
          roadSchedule: JSON.stringify(this.formMsg)
        };
        addManyTimes(param).then(res => {
          this.$emit('fatherMethod');
          alert(res);
        })
        this.dialogVisible = false
      }else{
        alert("请输入您要配置的线路");
      }
    },
    parentClick(){
      this.dialogVisible=true;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    }
  },
  mounted() {
    this.loadAllBusNo();
  }
};
</script>
