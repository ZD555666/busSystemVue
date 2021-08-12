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
                <el-input v-model="busNo" placeholder="请输入线路">
                </el-input>
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
      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>

    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
  }
};
</script>
