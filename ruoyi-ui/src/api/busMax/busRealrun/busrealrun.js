import request from '@/utils/request'

// 查询busRealrun列表
export function listBusrealrun(query) {
  return request({
    url: '/busRealrun/busrealrun/list',
    method: 'get',
    params: query
  })
}

// 查询busRealrun详细
export function getBusrealrun(realrunid) {
  return request({
    url: '/busRealrun/busrealrun/' + realrunid,
    method: 'get'
  })
}

// 新增busRealrun
export function addBusrealrun(data) {
  return request({
    url: '/busRealrun/busrealrun',
    method: 'post',
    data: data
  })
}

// 修改busRealrun
export function updateBusrealrun(data) {
  return request({
    url: '/busRealrun/busrealrun',
    method: 'put',
    data: data
  })
}

// 删除busRealrun
export function delBusrealrun(realrunid) {
  return request({
    url: '/busRealrun/busrealrun/' + realrunid,
    method: 'delete'
  })
}

// 导出busRealrun
export function exportBusrealrun(query) {
  return request({
    url: '/busRealrun/busrealrun/export',
    method: 'get',
    params: query
  })
}