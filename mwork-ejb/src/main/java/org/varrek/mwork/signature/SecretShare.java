/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.varrek.mwork.signature;

import java.math.BigInteger;

/**
 *
 * @author Varrep
 */
public final class SecretShare {
    private final Shamir outer;

    public SecretShare(final int num, final BigInteger share, final Shamir outer) {
        this.outer = outer;
        this.num = num;
        this.share = share;
    }

    public SecretShare(final Shamir outer) {
        this.outer = outer;
        this.num = 0;
        this.share = new BigInteger("0");
    }

    public int getNum() {
        return num;
    }

    public BigInteger getShare() {
        return share;
    }

    @Override
    public String toString() {
        return "SecretShare [num=" + num + ", share=" + share + "]";
    }
    private final int num;
    private final BigInteger share;
    
}
