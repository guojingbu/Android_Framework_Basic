package com.yesway.api_lib.config;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/9/21
 */
public interface CommonConfig {
    /**
     * 通用常量
     */
    interface COMMON {
        interface REQUEST {

        }

        interface KEY {
            String APP_VERSION = "app_version";
        }

        interface VALUE {

        }

    }

    /**
     * 主模块常量
     */
    interface MAIN {
        interface REQUEST {

        }

        interface KEY {
            String KEY_USER_INFO = "user";
        }

        interface VALUE {

        }

    }

    /**
     * 主页模块常量
     */
    interface Order {
        interface REQUEST {

        }

        interface KEY {

            String KEY_ORDER = "order";
        }

    }

    /**
     * 我的模块常量
     */
    interface ME {
        interface REQUEST {
        }

        interface KEY {
        }


    }
}
