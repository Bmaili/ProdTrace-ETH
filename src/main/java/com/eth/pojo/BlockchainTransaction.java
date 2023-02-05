package com.eth.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlockchainTransaction {
    private String id;
    private int fromId;
    private int toId;
    private long value;
    private boolean accepted;
}
