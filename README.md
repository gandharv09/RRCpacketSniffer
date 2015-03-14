# RRCpacketSniffer
RRCpacketSniffer is an android application which can be used for capturing tcp udp and icmp data packets and resulting RRC state transitions, simultaneously.

The application is working implementation of the tool discussed in this research paper [Android phone based appraisal of app behavior on cell networks](http://dl.acm.org/citation.cfm?id=2593916)

[![RRCpacketSniffer](http://www.weathercasterapp.com/img/appstore_google.png)](https://play.google.com/store/apps/details?id=com.iiitd.RRCpacketSniffer)

## Description
Quantifying an application’s signaling eﬃciency requires information about:
* The data packets that were exchanged. 
* The RRC state transitions that took place.. 

Unlike the existing applications and tools, this application neither requires any special hardware, nor retrieves RRC state in an oﬄine manner using a simulator. 
The functionality of the tool is split into two parts, the **Packet Sniffer** and the **RRC State Logger**.
  
  

![](https://github.com/gandharv09/RRCpacketSniffer/blob/master/SC20150314-030848.png) ![](https://github.com/gandharv09/RRCpacketSniffer/blob/master/SC20150301-0330131.png)


### Packet Sniffer 
The packet sniﬀer uses *C_packetCapture* a binary executable of libpcap, built using libpcap source code and Android NDK. The executable is launched in a shell with superuser privileges.
The packet sniﬀer enables us to capture all uplink and downlink packets associated with a network interface, and stores the packet timestamp and header information corresponding to IP, UDP, TCP, and ICMP protocols, in a log.text ﬁle.
###RRC State Logger
For Retrieving RRC States, we adopt a novel approach to ﬁnd and record the actual RRC state of the device at any given instant. Android Secret codes are required to switch to RRC service mode. 
Currently we have secret codes for limited samsung devices, though a wide variety of secret codes are available for different manufacturers.

The detailed explaination of calucalting an application's signalling efficiency from the data collected is described in this paper [Android phone based appraisal of app behavior on cell networks](http://dl.acm.org/citation.cfm?id=2593916)

## Devices Supported
The application currently supports all major Samsung devices ranging from Galaxy S to Galaxy S5 and **requires root permission for full functionality**. We are working to extend this to other device brands as well.

## Authors & Contributors
* Gandharv Kapoor, Indraprastha Institute of Information Technology, Delhi
* Nikita Jain, Indraprastha Institute of Information Technology, Delhi
* Shaifali Gupta, Indraprastha Institute of Information Technology, Delhi
* Rashi Garg, Indraprastha Institute of Information Technology, Delhi
* [Vinayak Naik](http://www.iiitd.edu.in/~naik/), Indraprastha Institute of Information Technology, Delhi

## License

Copyright (c), Indraprastha Institute of Information Technology Delhi

Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice is mentioned and research paper is cited.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
