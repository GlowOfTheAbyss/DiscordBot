package org.glow.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.glow.item.Item;
import org.glow.item.body.Body;
import org.glow.item.body.NoneBody;
import org.glow.item.finger.Finger;
import org.glow.item.finger.NoneFinger;
import org.glow.item.head.NoneHead;
import org.glow.item.lefthand.LeftHand;
import org.glow.item.lefthand.NoneLeftHand;
import org.glow.item.legs.NoneLegs;
import org.glow.item.neck.NoneNeck;
import org.glow.item.righthand.NoneRightHand;
import org.glow.item.righthand.RightHand;
import org.glow.item.head.Head;
import org.glow.item.legs.Legs;
import org.glow.item.neck.Neck;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private Head head;
    private Body body;
    private Legs legs;
    private RightHand rightHand;
    private LeftHand leftHand;

    private Neck neck;
    private Finger finger;

    private List<Item> bag = new ArrayList<>();
    private int bagSize;

    public Inventory() {

        this.head = new NoneHead();
        this.body = new NoneBody();
        this.legs = new NoneLegs();
        this.rightHand = new NoneRightHand();
        this.leftHand = new NoneLeftHand();

        this.neck = new NoneNeck();
        this.finger = new NoneFinger();

        this.bagSize = 10;

    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Legs getLegs() {
        return legs;
    }

    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    public RightHand getRightHand() {
        return rightHand;
    }

    public void setRightHand(RightHand rightHand) {
        this.rightHand = rightHand;
    }

    public LeftHand getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(LeftHand leftHand) {
        this.leftHand = leftHand;
    }

    public Neck getNeck() {
        return neck;
    }

    public void setNeck(Neck neck) {
        this.neck = neck;
    }

    public Finger getFinger() {
        return finger;
    }

    public void setFinger(Finger finger) {
        this.finger = finger;
    }

    public List<Item> getBag() {
        return bag;
    }

    public void setBag(List<Item> bag) {
        this.bag = bag;
    }

    public int getBagSize() {
        return bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }

}
