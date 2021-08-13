import request from '@/utils/request'

// 查询司机排班列表
export function listdriverSchedule(query) {
  return request({
    url: '/driverManager/driverSchedule/list',
    method: 'get',
    params: query
  })
}

// 查询司机排班详细
export function getdriverSchedule(driverworkid) {
  return request({
    url: '/driverManager/driverSchedule/' + driverworkid,
    method: 'get'

  })
}

// 新增司机排班
export function adddriverSchedule(data) {
  return request({
    url: '/driverManager/driverSchedule',
    method: 'post',
    data: data
  })
}

// 修改司机排班
export function updatedriverSchedule(data) {
  return request({
    url: '/driverManager/driverSchedule',
    method: 'put',
    data: data
  })
}

// 删除司机排班
export function deldriverSchedule(driverworkid) {
  return request({
    url: '/driverManager/driverSchedule/' + driverworkid,
    method: 'delete'
  })
}

// 导出司机排班
export function exportdriverSchedule(query) {
  return request({
    url: '/driverManager/driverSchedule/export',
    method: 'get',
    params: query
  })
}

//查询发车时刻和公交线路
export function selectBusSchedule(query){
  return request({
    url:'/driverManager/driverSchedule/busScheduleList',
    method:'get',
    params:query
  })
}

//  查询站点信息
export function selectStationInfolist(data){
  return request({
    url:'/driverManager/driverSchedule/StationInfolist',
    method:'post',
    data:data

  })
}

// 新增司机排班
export function addDialogInputInfo(data) {
  return request({
    url: '/driverManager/driverSchedule/dialogInputInfoList',
    method: 'post',
    data: data
  })
}

//查询公交车信息
export function selectBusnoInformationList(data) {
  return request({
    url: '/driverManager/driverSchedule/busnoInformationList',
    method: 'post',
    data: data
  })
}

//查询公交车信息
export function selectBusPlateInformationList(data) {
  return request({
    url: '/driverManager/driverSchedule/busPlateInformationList',
    method: 'post',
    data: data
  })
}

//查询司机信息
export function selectBusDriverInformationList(data) {
  return request({
    url: '/driverManager/driverSchedule/busDriverInformationList',
    method: 'post',
    data: data
  })
}


