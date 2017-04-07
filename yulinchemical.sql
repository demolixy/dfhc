select * from  user_tables;
SELECT * FROM BASE_IMPULSE_SENDER;
---------
select * from BUS_VEHICLE_SAFETY_INSPECTION;
----------
select * from BUS_SECURITY_ITEM_RESULT t where t.vehicle_safety_inspection_id='4020201704070007001';

------
select * from BASE_SECURITY_INDEX t where t.check_type='03';

select * from BUS_VEHICLE_SAFETY_INSPECTION t where t.id='4020201704070005001';
------------
BUS_VEHICLE_SAFETY_INSPECTION


select t.rowid, t.delete_flag, t.status, t.*
  from BUS_VEHICLE_SAFETY_INSPECTION t
 where t.lading_bill_id = '4034201704070000003';
-- where t.lading_bill_id = '4020201704070005001'
 #############
select t.rowid, t.lading_bill_status, t.delete_flag, t.*
  from BUS_LADING_BILL t
 where t.id = '4034201704070000003';

select t.rowid,t.status ,t.* from BUS_ROW_NUMBER  t where t.lading_bill_id='4034201704070000003';



  select *
          from (select row_.*, rownum rownum_
            from (select distinct BUS_LADING_BILL.ID as id,
                                  BUS_LADING_BILL.ORDER_PLAN_ID as orderPlanId,
                                  BUS_LADING_BILL.ORDER_PLAN_CODE as orderPlanCode,
                                  BUS_LADING_BILL.LADING_BILL_CODE as ladingBillCode,
                                  BUS_LADING_BILL.BILL_TYPE as billType,
                                  BUS_LADING_BILL.SHIP_MODE as shipMode,
                                  BUS_LADING_BILL.CAR_TYPE as carType,
                                  BUS_LADING_BILL.DRIVER_NAME as driverName,
                                  BUS_LADING_BILL.DRIVER_ID_CARD_NUMBER as driverIdCardNumber,
                                  nvl("check".status, '2') as ladingBillStatus,
                                  BUS_LADING_BILL.IS_LOADING as isLoading,
                                  BUS_LADING_BILL.PRODUCT_UNIT_PRICE as productUnitPrice,
                                  BUS_LADING_BILL.ACTUAL_SHIPPING_AMOUNT as actualShippingAmount,
                                  BUS_LADING_BILL.PLANNED_SHIPMENT_AMOUNT as plannedShipmentAmount
                    from BUS_LADING_BILL
                   inner join BASE_PRODUCT
                      on BASE_PRODUCT.ID = BUS_LADING_BILL.PRODUCT_ID
                   inner join BUS_ROW_NUMBER
                      on BUS_ROW_NUMBER.LADING_BILL_ID =
                         BUS_LADING_BILL.ID
                    left join (select BUS_VEHICLE_SAFETY_INSPECTION.LADING_BILL_ID as ladingBillId,
                                     BUS_VEHICLE_SAFETY_INSPECTION.STATUS         as status
                                from BUS_VEHICLE_SAFETY_INSPECTION
                               where nvl(BUS_VEHICLE_SAFETY_INSPECTION.DELETE_FLAG,
                                         '0') = '0') "check"
                      on "check".ladingBillId = BUS_LADING_BILL.ID
                   where BUS_LADING_BILL.LADING_BILL_STATUS = '04'
                    and nvl(BUS_LADING_BILL.DELETE_FLAG, '0') = '0'
                    and nvl(BUS_ROW_NUMBER.DELETE_FLAG, '0') = '0'
                 and to_char(BUS_ROW_NUMBER.ROW_DATE, 'YYYY-MM-DD') = '2017-04-07'
                   and BUS_ROW_NUMBER.STATUS = '05'
                 and nvl("check".status, '2') = '2'
                     and BASE_PRODUCT.STORE_MODE = '00') row_)
         where rownum_ > 0
           and rownum_ <= 15


