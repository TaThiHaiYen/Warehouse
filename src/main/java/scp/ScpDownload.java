package scp;

import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

import connection.MyConnection;

public class ScpDownload {
	static {
		try {
			// copy file chilkat.dll vào thư mục project

			System.loadLibrary("chilkat");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	public void getTrial() {
		CkGlobal glob = new CkGlobal();
		boolean success = glob.UnlockBundle("Anything for 30-day trial");
		if (success != true) {
			System.out.println(glob.lastErrorText());
			return;
		}
		int status = glob.get_UnlockStatus();
		if (status == 2) {
			System.out.println("Unlocked using purchased unlock code.");
		} else {
			System.out.println("Uncloked in trail mode.");
		}
		System.out.println(glob.lastErrorText());
	}

	public String downloadFile() {
		// This example requires the Chilkat API to have been previously unlocked.
		// See Global Unlock Sample for sample code.

		CkSsh ssh = new CkSsh();
		ScpDownload gg = new ScpDownload();
		gg.getTrial();
//		String hostname = "drive.ecepvn.org";
		String hostname = Download.host;
		int port = Download.port;

		// Connect to an SSH server:
		boolean success = ssh.Connect(hostname, port);
		if (success != true) {
			System.out.println(ssh.lastErrorText());
			return "fail";
		}

		// Wait a max of 5 seconds when reading responses..
		ssh.put_IdleTimeoutMs(5000);

		// Authenticate using login/password:
		success = ssh.AuthenticatePw(Download.user, Download.pw);
		if (success != true) {
			System.out.println(ssh.lastErrorText());
			return "fail";
		}

		// Once the SSH object is connected and authenticated, we use it
		// in our SCP object.
		CkScp scp = new CkScp();

		success = scp.UseSsh(ssh);
		if (success != true) {
			return scp.lastErrorText();
		}
		// down tất cả file bắt đầu bằng sinhvien

		scp.put_SyncMustMatch("sinhvien*.*xlsx");
		String remotePath = Download.remotePath;
		// Thư mục muốn down file về
		String localPath = Download.localPath;
		success = scp.SyncTreeDownload(remotePath, localPath, 2, false);

		if (success != true) {
			return scp.lastErrorText();
		}

		ssh.Disconnect();
		return "success";
		// Disconnect
	}
}