package aoc2021;

import java.util.*;


public class Day16 {

	public String solve(boolean part1, Scanner in) {
		String b = "";
		String[] hex = in.nextLine().split("");
		for(int i = 0; i < hex.length; i++) {
			Hex h = new Hex(hex[i]);
			b+= h.binary;
		}
		String[] binary = b.split("");
		Packet packet = new Packet(binary);
		return "" + (part1?packet.getTotalVersions():packet.value());
	}
}

class Hex{
	int val;
	int[] bits = new int[4];
	String str;
	String binary;
	
	public Hex(String s) {
		str = s;
		val = (int)Long.parseLong(s, 16);
		bits[0] = val/8;
		bits[1] = (val%8)/4;
		bits[2] = (val%4)/2;
		bits[3] = val%2;
		binary = "" + bits[0] + "" + bits[1] + "" + bits[2] + "" + bits[3];
	}
}

class Packet{
	long version;
	long type_id;
	List<Packet> subPackets;
	long subPacketLength;
	long value = 0;
	long length;
	long length_type_id;
	
	public Packet(String[] binary) {
		subPackets = new ArrayList<>();
		int index = 0;
		version = Long.parseLong(readN(binary, 3, index),2);
		index+=3;
		type_id = Long.parseLong(readN(binary, 3, index),2);
		index+=3;
		if(type_id == 4) {
			String binaryRep = "";
			while(binary[index].equals("1")) {
				binaryRep+=readN(binary, 4, index + 1);
				index+=5;
			}
			binaryRep+=readN(binary, 4, index + 1);
			index+=5;
			value = Long.parseLong(binaryRep,2);
			length = index;
		}
		else {
			length_type_id = Long.parseLong(binary[index],2);
			index++;
			if(length_type_id == 0) {
				subPacketLength = 15;
				String binaryRep = readN(binary, subPacketLength, index);
				index+=subPacketLength;
				long numbits = Long.parseLong(binaryRep, 2);
				String remaining = readN(binary, numbits, index);
				while(numbits > 0) {
					Packet subPacket = new Packet(remaining.split(""));
					numbits -= subPacket.length;
					remaining = remaining.substring((int)subPacket.length);
					index+= subPacket.length;
					subPackets.add(subPacket);
				}
				length = index;
			}
			if(length_type_id == 1) {
				subPacketLength = 11;
				String binaryRep = readN(binary, subPacketLength, index);
				index+=subPacketLength;
				long numPackets = Long.parseLong(binaryRep, 2);
				String remaining = readN(binary, binary.length - index, index);
				while(numPackets > 0) {
					Packet subPacket = new Packet(remaining.split(""));
					numPackets--;
					remaining = remaining.substring((int)subPacket.length);
					index+= subPacket.length;
					subPackets.add(subPacket);
				}
				length = index;
			}
		}
	}
	
	private String readN(String[] binary, long n, int index) {
		int counter = 0;
		String result = "";
		while(counter < n) {
			result+=binary[index + counter];
			counter++;
		}
		return result;
	}
	
	public long getTotalVersions() {
		long answer = version;
		for(Packet packet: subPackets) {
			answer+= packet.getTotalVersions();
		}
		return answer;
	}
	
	public long value() {
		if(type_id == 4) {
			return value;
		}
		if(type_id == 0) {
			long answer = 0;
			for(Packet packet: subPackets) {
				answer+= packet.value();
			}
			return answer;
		}
		if(type_id == 1) {
			long answer = 1;
			for(Packet packet: subPackets) {
				answer*= packet.value();
			}
			return answer;
		}
		if(type_id == 2) {
			long answer = Long.MAX_VALUE;
			for(Packet packet: subPackets) {
				answer = Math.min(answer,  packet.value());
			}
			return answer;
		}
		if(type_id == 3) {
			long answer = Long.MIN_VALUE;
			for(Packet packet: subPackets) {
				answer = Math.max(answer,  packet.value());
			}
			return answer;
		}
		
		if(type_id == 5) {
			return (subPackets.get(0).value() > subPackets.get(1).value())?1:0;
		}
		if(type_id == 6) {
			return (subPackets.get(0).value() < subPackets.get(1).value())?1:0;
		}
		if(type_id == 7) {
			return (subPackets.get(0).value() == subPackets.get(1).value())?1:0;
		}
		
		return -1;
	}
}