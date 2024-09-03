-- 建立DB順序
create database orders;

CREATE TABLE orders.order_information (
      id INT auto_increment PRIMARY KEY, -- 使用 INT 且設定自動遞增，初始值為 1，每次遞增 1
      name NVARCHAR(255) NOT NULL, -- 使用 NVARCHAR 適應國際化需求
      order_qty INT NOT NULL, -- 使用 INT 作為數量欄位
      actual_qty INT NOT NULL, -- 使用 INT 作為數量欄位
      price INT NOT NULL, -- 使用 INT 作為價格欄位
      payer_name NVARCHAR(255) NOT NULL, -- 使用 NVARCHAR 適應國際化需求
      phone NVARCHAR(50), -- 使用 NVARCHAR 存儲電話號碼
      email NVARCHAR(255), -- 使用 NVARCHAR 存儲電子郵件地址
      address NVARCHAR(255), -- 使用 NVARCHAR 存儲地址
      payment_name NVARCHAR(100), -- 使用 NVARCHAR 適應國際化需求
      payment_status NVARCHAR(50), -- 使用 NVARCHAR 存儲付款狀態
      status INT NOT NULL -- 使用 INT 作為狀態欄位
);