<template>
  <div class="body">
    <div class="demo-image">
      <div class="block" v-for="fit in fits" :key="fit">
        <el-row style="text-align: center">
          <span class="demonstration"
                style="color:#1890FF;font-weight:600;font-family:”微软雅黑”;font-size: 30px">{{
              fit
            }}</span>
        </el-row>
        <el-row>
          <el-image
            style="width: 100%;"
            :src="url"
            :fit="contain"></el-image>
        </el-row>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fits: ['智慧公交系统'],
      url: '/bg.png'
    }
  }
}
</script>

<style>
body html{
  /*background-image: url("../assets/images/bg.png");*/
  padding: 0;
  magin: 0;
  height: 100%;
  width: 100%;
}

</style>
