import request from '@/utils/request'

// 查询小程序用户列表
export function listUser(query) {

  console.log(process.env.VUE_APP_BASE_API)
  return request({
    url: '/xcxuser/user/list',
    method: 'get',
    params: query
  })
}

// 查询小程序用户详细
export function getUser(id) {
  return request({
    url: '/xcxuser/user/' + id,
    method: 'get'
  })
}

// 新增小程序用户
export function addUser(data) {
  return request({
    url: '/xcxuser/user',
    method: 'post',
    data: data
  })
}

// 修改小程序用户
export function updateUser(data) {
  return request({
    url: '/xcxuser/user',
    method: 'put',
    data: data
  })
}

// 删除小程序用户
export function delUser(id) {
  return request({
    url: '/xcxuser/user/' + id,
    method: 'delete'
  })
}

// 导出小程序用户
export function exportUser(query) {
  return request({
    url: '/xcxuser/user/export',
    method: 'get',
    params: query
  })
}
