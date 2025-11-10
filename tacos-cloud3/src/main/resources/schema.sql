-- Drop tables in reverse order to avoid FK conflicts (safe in dev)
drop table if exists Ingredient_Ref;
drop table if exists Taco;
drop table if exists Taco_Order;
drop table if exists Ingredient;

-- ===================================
-- 1. Taco_Order (the order header)
-- ===================================
create table if not exists Taco_Order (
                                          id identity primary key,
                                          delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    -- FIXED: changed from varchar(2) to varchar(20) 
    -- so "California", "Ontario", "NSW", or even "USA" no longer crash
    delivery_state varchar(20) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );

-- ===================================
-- 2. Taco (one taco design inside an order)
-- ===================================
create table if not exists Taco (
                                    id identity primary key,
                                    name varchar(50) not null,
    taco_order bigint not null,           -- references the order
    created_at timestamp not null,
    foreign key (taco_order) references Taco_Order(id)
    );

-- ===================================
-- 3. Ingredient (master list)
-- ===================================
create table if not exists Ingredient (
                                          id varchar(4) primary key,    -- e.g. FLTO, GRBF, CHED
    name varchar(25) not null,
    type varchar(10) not null     -- PROTEIN, CHEESE, VEGGIES, SAUCE, WRAP
    );

-- ===================================
-- 4. Ingredient_Ref (many-to-many join table)
-- ===================================
create table if not exists Ingredient_Ref (
                                              ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null,     -- needed for ordered list in Spring Data JDBC
    primary key (taco, taco_key),
    foreign key (ingredient) references Ingredient(id),
    foreign key (taco) references Taco(id)
    );

-- ===================================
-- Optional: useful indexes for performance
-- ===================================
create index if not exists idx_taco_order_placed_at on Taco_Order(placed_at desc);
create index if not exists idx_taco_taco_order on Taco(taco_order);