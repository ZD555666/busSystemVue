import request from '@/utils/request'

// 查询站点信息列表
export function listStationinfo(query) {
  return request({
    url: '/road/stationinfo/list',
    method: 'get',
    params: query
  })
}

// 查询站点信息详细
export function getStationinfo(stationid) {
  return request({
    url: '/road/stationinfo/' + stationid,
    method: 'get'
  })
}

// 新增站点信息
export function addStationinfo(data) {
  return request({
    url: '/road/stationinfo',
    method: 'post',
    data: data
  })
}

// 修改站点信息
export function updateStationinfo(data) {
  return request({
    url: '/road/stationinfo',
    method: 'put',
    data: data
  })
}

// 删除站点信息
export function delStationinfo(stationid) {
  return request({
    url: '/road/stationinfo/' + stationid,
    method: 'delete'
  })
}

// 导出站点信息
export function exportStationinfo(query) {
  return request({
    url: '/road/stationinfo/export',
    method: 'get',
    params: query
  })
}