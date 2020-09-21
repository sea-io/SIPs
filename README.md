
# SIPs
<<<<<<< HEAD
EIP Status Terms
Draft - an EIP that is undergoing rapid iteration and changes.
Last Call - an EIP that is done with its initial iteration and ready for review by a wide audience.
Accepted - a core EIP that has been in Last Call for at least 2 weeks and any technical changes that were requested have been addressed by the author. The process for Core Devs to decide whether to encode an EIP into their clients as part of a hard fork is not part of the EIP process. If such a decision is made, the EIP will move to final.
Final (non-Core) - an EIP that has been in Last Call for at least 2 weeks and any technical changes that were requested have been addressed by the author.
Final (Core) - an EIP that the Core Devs have decided to implement and release in a future hard fork or has already been released in a hard fork.
=======
func TestGenData(t *testing.T) {
	t.Logf("dummy color: %s", sctransaction.RandomColor().String())
	t.Logf("dummy owner's address: %s", address.RandomOfType(address.VersionBLS).String())
	t.Logf("dummy program hash: %s", hashing.HashStrings("dummy program").String())
}
>>>>>>> 3c8376d402ffd3a3697044a4dd85b405c2f90088
