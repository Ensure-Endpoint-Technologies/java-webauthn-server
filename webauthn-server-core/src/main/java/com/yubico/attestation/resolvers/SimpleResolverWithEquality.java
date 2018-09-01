package com.yubico.attestation.resolvers;

import com.yubico.attestation.MetadataObject;
import java.security.cert.X509Certificate;

/**
 * Resolves a metadata object whose associated certificate has signed the
 * argument certificate, or is equal to the argument certificate.
 */
public class SimpleResolverWithEquality extends SimpleResolver {

    @Override
    public MetadataObject resolve(X509Certificate attestationCertificate) {
        MetadataObject parentResult = super.resolve(attestationCertificate);

        if (parentResult == null) {
            for (X509Certificate cert : certs.get(attestationCertificate.getSubjectDN().getName())) {
                if (cert.equals(attestationCertificate)) {
                    return metadata.get(cert);
                }
            }
        }

        return null;
    }

}
