import request from '@/utils/request'

// 查询driverSalary列表
export function listDriverSalary(query) {
  return request({
    url: '/driverSalary/driverSalary/list',
    method: 'get',
    params: query
  })
}

// 查询driverSalary详细
export function getDriverSalary(salaryid) {
  return request({
    url: '/driverSalary/driverSalary/' + salaryid,
    method: 'get'
  })
}

// 新增driverSalary
export function addDriverSalary(data) {
  return request({
    url: '/driverSalary/driverSalary',
    method: 'post',
    data: data
  })
}

// 修改driverSalary
export function updateDriverSalary(data) {
  return request({
    url: '/driverSalary/driverSalary',
    method: 'put',
    data: data
  })
}

// 删除driverSalary
export function delDriverSalary(salaryid) {
  return request({
    url: '/driverSalary/driverSalary/' + salaryid,
    method: 'delete'
  })
}

// 导出driverSalary
export function exportDriverSalary(query) {
  return request({
    url: '/driverSalary/driverSalary/export',
    method: 'get',
    params: query
  })
}