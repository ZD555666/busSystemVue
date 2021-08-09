import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import baiduUser from './modules/baiduUser'
import getters from './getters'
import roadInfo from './modules/roadInfo'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings,
    baiduUser,
    roadInfo
  },
  getters
})

export default store
