import request from '@/utils/request'

// 查询线路配置列表
export function listRoads(query) {
  return request({
    url: '/road/roads/list',
    method: 'get',
    params: query
  })
}

//查询有配置的city
export function cityOption(){
  return request({
    url: '/road/roads/cityList',
    method: 'get'
  })
}

// 新增线路配置
export function addRoads(data) {
  return request({
    url: '/road/roads/addRoads',
    method: 'get',
    params: data,
    config:{headers:{'Content-Type':'application/json;charset=UTF-8'}}
  })
}

// 修改线路配置
export function updateRoads(params) {
  return request({
    url: '/road/roads/updateRoad',
    method: 'get',
    params: params
    // config: {headers: {'Content-Type': 'application/json;charset=UTF-8'}}
  })
}

// 删除线路配置
export function deleteRoad(params) {
  return request({
    url: '/road/roads/deleteRoad',
    method: 'get',
    params: params
  })
}

