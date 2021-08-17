import request from '@/utils/request'

// 查询投诉建议管理列表
export function listComplaint(query) {
  return request({
    url: '/complaint/complaint/list',
    method: 'get',
    params: query
  })
}

// 查询投诉建议管理详细
export function getComplaint(complaintid) {
  return request({
    url: '/complaint/complaint/' + complaintid,
    method: 'get'
  })
}

// 新增投诉建议管理
export function addComplaint(data) {
  return request({
    url: '/complaint/complaint',
    method: 'post',
    data: data
  })
}

// 修改投诉建议管理
export function updateComplaint(data) {
  return request({
    url: '/complaint/complaint',
    method: 'put',
    data: data
  })
}

// 删除投诉建议管理
export function delComplaint(complaintid) {
  return request({
    url: '/complaint/complaint/' + complaintid,
    method: 'delete'
  })
}

// 导出投诉建议管理
export function exportComplaint(query) {
  return request({
    url: '/complaint/complaint/export',
    method: 'get',
    params: query
  })
}