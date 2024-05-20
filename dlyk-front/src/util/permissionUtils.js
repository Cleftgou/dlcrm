// 检查用户是否具有指定权限
export function hasPermission(user, permission) {
    return user.permissionList.includes(permission);
}

// 封装常见的权限验证逻辑
export default {
    //市场活动权限按钮显示
    canAddActivity(user) {
        return hasPermission(user, 'activity:add');
    },
    canViewActivity(user) {
        return hasPermission(user, 'activity:view');
    },
    canDeleteActivity(user) {
        return hasPermission(user, 'activity:delete');
    },
    canEditActivity(user) {
        return hasPermission(user, 'activity:edit');
    },
    canSearchActivity(user) {
        return hasPermission(user, 'activity:search');
    },


    //线索权限按钮显示
    canAddClue(user) {
        return hasPermission(user, 'clue:add');
    },
    canViewClue(user) {
        return hasPermission(user, 'clue:view');
    },
    canDeleteClue(user) {
        return hasPermission(user, 'clue:delete');
    },
    canEditClue(user) {
        return hasPermission(user, 'clue:edit');
    },
    canImportClue(user) {
        return hasPermission(user, 'clue:import');
    },

    //产品权限按钮显示
    canAddProduct(user) {
        return hasPermission(user, 'product:add');
    },
    canViewProduct(user) {
        return hasPermission(user, 'product:view');
    },
    canDeleteProduct(user) {
        return hasPermission(user, 'product:delete');
    },
    canEditProduct(user) {
        return hasPermission(user, 'product:edit');
    },

    //交易权限按钮显示
    canAddTran(user) {
        return hasPermission(user, 'tran:view');
    },
    canViewTran(user) {
        return hasPermission(user, 'tran:view');
    },
    canDeleteTran(user) {
        return hasPermission(user, 'tran:view');
    },
    canEditTran(user) {
        return hasPermission(user, 'tran:view');
    },
}