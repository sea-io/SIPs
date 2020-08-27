#!/usr/bin/env bash
zli contract call -a ${MULTISIG_WALLET_ADDRESS} -t ExecuteTransaction -s ${KEY_STORE_PATH} -r "[{\"vname\":\"transactionId\",\"type\":\"Uint32\",\"value\":\"${TRANSACTION_ID}\"}]" -f true