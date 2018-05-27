package com.tmobile.util;

public class Types {
    public enum Role {
        ROLE_CUSTOMER("ROLE_CUSTOMER"),
        ROLE_MANAGER("ROLE_MANAGER");

        private final String value;

        Role(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum ContractBlocked {
//        UNBLOCKED,
//        BLOCKED_BY_CUSTOMER,
//        BLOCKED_BY_MANAGER;
        UNBLOCKED(0),
        BLOCKED_BY_CUSTOMER(1),
        BLOCKED_BY_MANAGER(2);

        private final int value;
        ContractBlocked(int val) {
            this.value = val;
        }

        public int getVal() {
            return this.value;
        }
    }
}
