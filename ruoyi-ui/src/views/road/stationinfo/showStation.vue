<template>
  <div class="bmap-page-container">
    <el-bmap vid="bmapDemo" :zoom="zoom" :center="center" class="bmap-demo">
      <el-bmapv-view>
        <el-bmapv-marker-list-layer :visible="visible" :data="data"></el-bmapv-marker-list-layer>
      </el-bmapv-view>
    </el-bmap>
    <div>
      <button @click.prevent="switchVisible">切换显隐</button>
    </div>
  </div>
</template>

<style>
.bmap-demo {
  height: 550px;
}
</style>

<script>
module.exports = {
  name: 'bmap-page',
  data() {
    return {
      zoom: 14,
      center: [118.123095, 24.476071],
      data: [
        {
          geometry: {
            type: 'Point',
            coordinates: [118.123095, 24.476071],
          },
          properties: {
            text: '火车站',
            fillSize: 50,
            shadowSize: 100
          }
        },
        {
        geometry: {
          type: 'Point',
          coordinates: [118.195944, 24.5186264],
        },
        properties: {
          text: '五通站',
          fillSize: 20,
          shadowSize: 10,
          fontColor: 'red'
        }
      }, {
        geometry: {
          type: 'Point',
          coordinates: [118.125051, 24.482071],
        },
        properties: {
          text: '莲坂外图书城站',
          fillSize: 50,
          shadowSize: 100
        }
      }
      ],
      visible: true
    };
  },
  mounted() {
  },
  methods: {
    switchVisible() {
      this.visible = !this.visible;
    }
  }
};
</script>
