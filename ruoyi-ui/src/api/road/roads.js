import request from '@/utils/request'

// 查询线路配置列表
export function listRoads(query) {
  return request({
    url: '/road/roads/list',
    method: 'get',
    params: query
  })
}

// 查询线路配置详细
export function getRoads(roadid) {
  return request({
    url: '/road/roads/' + roadid,
    method: 'get'
  })
}

// 新增线路配置
export function addRoads(data) {
  return request({
    url: '/road/roads',
    method: 'post',
    data: data
  })
}

// 修改线路配置
export function updateRoads(data) {
  return request({
    url: '/road/roads',
    method: 'put',
    data: data
  })
}

// 删除线路配置
export function delRoads(roadid) {
  return request({
    url: '/road/roads/' + roadid,
    method: 'delete'
  })
}

// 导出线路配置
export function exportRoads(query) {
  return request({
    url: '/road/roads/export',
    method: 'get',
    params: query
  })
}