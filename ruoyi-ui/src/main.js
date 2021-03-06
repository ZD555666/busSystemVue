import Vue from 'vue'

import Cookies from 'js-cookie'

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import directive from './directive' //directive
import axios from "axios";
Vue.prototype.$axios = axios;

import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";
// 自定义表格工具组件
import RightToolbar from "@/components/RightToolbar"
// 富文本组件
import Editor from "@/components/Editor"
// 文件上传组件
import FileUpload from "@/components/FileUpload"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload"
// 字典标签组件
import DictTag from '@/components/DictTag'

import VueMeta from 'vue-meta'
//引入日历FullCalendar
// import FullCalendar from 'vue-full-calendar' //全局
// import 'fullcalendar/dist/fullcalendar.css'
// //引入moment
import moment from "moment"

// 百度地图api
import BaiduMap from 'vue-baidu-map'
import {BmGeolocation, BmlMarkerClusterer, BmLocalSearch} from "vue-baidu-map/components";

Vue.use(BaiduMap, {
  ak: 'kNpAEcc1tXTkS1eQVzRtVwtwdC6c9TLq'
})
Vue.component('bm-marker-clusterer', BmlMarkerClusterer)
Vue.component('bm-local-search', BmLocalSearch)
Vue.component('bm-geolocation', BmGeolocation)


// 百度地图数据可视化组件vue-mapvgl
import VueBMap from 'vue-bmap-gl';
import 'vue-bmap-gl/dist/style.css'
import VueMapvgl from 'vue-mapvgl';
Vue.use(VueBMap);
Vue.use(VueMapvgl);
VueBMap.initBMapApiLoader({
  ak: 'kNpAEcc1tXTkS1eQVzRtVwtwdC6c9TLq',
  v: '1.0'
})
import echarts from 'echarts'

Vue.prototype.$echarts = echarts

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

// 全局组件挂载
Vue.component('DictTag', DictTag)
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
Vue.component('Editor', Editor)
Vue.component('FileUpload', FileUpload)
Vue.component('ImageUpload', ImageUpload)

Vue.use(directive)
Vue.use(VueMeta)
// Vue.use(FullCalendar)
Vue.use(moment)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
