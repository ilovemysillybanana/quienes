# Quiénes

Why this name? For those of you who took a language in high school, it might be obvious. For those did not, it just means, "who" in Spanish.

## Why does this exist?

I was inspired to create this over a weekend when parsing horrible amounts of text given to a colleague and myself from multiple platforms using multiple methods that generated differing outputs. These files were just different enough to bring us great heartache.

## Goals

- Support AIX, Windows, Solaris, and Linux Operating Systems
- Return the output in a neatly formatted JSON File
- Never cry again

## Working

- AIX? ( I am not able to test this. )
- Linux
- Mac OS X
- Solaris
- Windows

## Instructions

Run the jar as if it were any other java application, you can send the output anywhere you'd like using standard terminal functions. You can specify an input file to designate groups with differing permissions and have that be reflected in your output.

```
java -jar quienes.jar
# redirect output
java -jar quienes.jar > output.json
```
#### specify input file

```
java -jar quienes.jar -f /path/to/file
```

#### Example Input File
Input files are in json format, it is expected that the team(s) running these should be able to categorize and their privileged users and groups.

```
{
  "elevated_groups": ["root", "jose"],
  "elevated_users": ["root", "rundeck", "jose"]
}
```

Each user will return an array of `elevated_groups`, if they belong to any. If they do not, but their name is contained by the group `elevated_users` they will return an `elevated_user: true`

## Expected Output

#### Without Input File
```
{
  "userdata": {
    "root": ["root"],
    "daemon": ["daemon"],
    "bin": ["bin"],
    "sys": ["sys"],
    "sync": [],
    "games": ["games"],
    "man": ["man"],
    "lp": ["lp"],
    "mail": ["mail"],
    "news": ["news"],
    "uucp": ["uucp"],
    "proxy": ["proxy"],
    "www-data": ["www-data"],
    "backup": ["backup"],
    "list": ["list"],
    "irc": ["irc"],
    "gnats": ["gnats"],
    "nobody": [],
    "systemd-timesync": ["systemd-timesync"],
    "systemd-network": ["systemd-network"],
    "systemd-resolve": ["systemd-resolve"],
    "systemd-bus-proxy": ["systemd-bus-proxy"],
    "syslog": ["syslog"],
    "_apt": [],
    "messagebus": ["messagebus"],
    "uuidd": ["uuidd"],
    "lightdm": ["lightdm"],
    "whoopsie": ["whoopsie"],
    "avahi-autoipd": ["avahi-autoipd"],
    "avahi": ["avahi"],
    "dnsmasq": [],
    "colord": ["colord"],
    "speech-dispatcher": [],
    "hplip": [],
    "kernoops": [],
    "pulse": ["audio", "pulse"],
    "rtkit": ["rtkit"],
    "saned": ["scanner", "saned"],
    "usbmux": [],
    "jose": ["cdrom", "sudo", "dip", "plugdev", "lpadmin", "jose", "sambashare", "vboxusers"],
    "postgres": ["ssl-cert", "postgres"],
    "sbt": ["sbt"],
    "openldap": ["openldap"],
    "rundeck": ["rundeck"]
  }
}
```

#### With Input File
```
{
  "userdata": {
    "nobody": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "root": {
      "elevated_user": true,
      "elevated_groups": [],
      "normal_groups": ["wheel", "daemon", "kmem", "sys", "tty", "operator", "procview", "procmod", "everyone", "staff", "certusers", "localaccounts", "admin", "com.apple.sharepoint.group.1", "_appstore", "_lpadmin", "_lpoperator", "_developer", "_analyticsusers", "com.apple.access_ftp", "com.apple.access_screensharing", "com.apple.access_ssh"]
    },
    "daemon": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["daemon", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_uucp": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["tty", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_taskgated": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_taskgated", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_networkd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_networkd", "everyone", "localaccounts", "_analyticsusers", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_installassistant": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_installassistant", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_lp": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_lp", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_postfix": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_postfix", "everyone", "certusers", "_keytabusers", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_scsd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_scsd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_ces": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_ces", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_appstore": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_appstore", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_mcxalr": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mcxalr", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_appleevents": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_appleevents", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_geod": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_geod", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_serialnumberd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_serialnumberd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_devdocs": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_devdocs", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_sandbox": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_sandbox", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_mdnsresponder": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mdnsresponder", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_ard": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_ard", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_www": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_www", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_eppc": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_eppc", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_cvs": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_cvs", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_svn": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_svn", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_mysql": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mysql", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_sshd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_sshd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_qtss": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_qtss", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_cyrus": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["mail", "everyone", "certusers", "localaccounts", "com.apple.sharepoint.group.1", "_keytabusers", "_lpoperator"]
    },
    "_mailman": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mailman", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_appserver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_appserverusr", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_clamav": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_clamav", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_amavisd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_amavisd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_jabber": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_jabber", "everyone", "certusers", "_keytabusers", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_appowner": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_appowner", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_windowserver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_windowserver", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_spotlight": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_spotlight", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_tokend": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_tokend", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_securityagent": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_securityagent", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_calendar": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_calendar", "everyone", "certusers", "_keytabusers", "localaccounts", "_postgres", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_teamsserver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_teamsserver", "mail", "everyone", "localaccounts", "_www", "_calendar", "_odchpass", "_postgres", "_webauthserver", "com.apple.sharepoint.group.1", "_keytabusers", "_lpoperator"]
    },
    "_update_sharing": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_installer": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_atsserver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_atsserver", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_ftp": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_unknown": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_unknown", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_softwareupdate": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_softwareupdate", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_coreaudiod": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_coreaudiod", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_screensaver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_screensaver", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_locationd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_locationd", "everyone", "localaccounts", "_detachedsig", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_trustevaluationagent": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_trustevaluationagent", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_timezone": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_timezone", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_lda": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_lda", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_cvmsroot": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_cvms", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_usbmuxd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_usbmuxd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_dovecot": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["mail", "everyone", "certusers", "localaccounts", "com.apple.sharepoint.group.1", "_keytabusers", "_lpoperator"]
    },
    "_dpaudio": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["groups=215", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_postgres": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_postgres", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krbtgt": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_kadmin_admin": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_kadmin_changepw": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_devicemgr": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_devicemgr", "everyone", "localaccounts", "_www", "_teamsserver", "_postgres", "_webauthserver", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_webauthserver": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_webauthserver", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_netbios": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_netbios", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_warmd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_warmd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_dovenull": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_dovenull", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_netstatistics": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_netstatistics", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_avbdeviced": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krb_krbtgt": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krb_kadmin": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krb_changepw": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krb_kerberos": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krb_anonymous": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_assetcache": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_assetcache", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_coremediaiod": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_coremediaiod", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_launchservicesd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_launchservicesd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_iconservices": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_iconservices", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_distnote": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_distnote", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_nsurlsessiond": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_nsurlsessiond", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_nsurlstoraged": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_nsurlstoraged", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_displaypolicyd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_displaypolicyd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_astris": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_astris", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_krbfast": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["nobody", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_gamecontrollerd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_gamecontrollerd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_mbsetupuser": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mbsetupuser", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_ondemand": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_ondemand", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_xserverdocs": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_xserverdocs", "everyone", "localaccounts", "_postgres", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_wwwproxy": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_wwwproxy", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_mobileasset": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_mobileasset", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_findmydevice": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_findmydevice", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_datadetectors": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_datadetectors", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_captiveagent": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_captiveagent", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_ctkd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_ctkd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_applepay": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_applepay", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_hidd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_hidd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_cmiodalassistants": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_cmiodalassistants", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_analyticsd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_analyticsd", "everyone", "localaccounts", "_analyticsusers", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_fpsd": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_fpsd", "everyone", "localaccounts", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "_timed": {
      "elevated_user": false,
      "elevated_groups": [],
      "normal_groups": ["_timed", "everyone", "localaccounts", "_analyticsusers", "com.apple.sharepoint.group.1", "_lpoperator"]
    },
    "jose": {
      "elevated_user": true,
      "elevated_groups": [],
      "normal_groups": ["staff", "com.apple.sharepoint.group.1", "everyone", "localaccounts", "_appserverusr", "admin", "_appserveradm", "_lpadmin", "access_bpf", "_appstore", "_lpoperator", "_developer", "_analyticsusers", "com.apple.access_ftp", "com.apple.access_screensharing", "com.apple.access_ssh"]
    }
  }
}
```

## Contributions

If you're interested in this project, you can help by writing tests, testing on different platforms, sending issues. Really any and all help is appreciated. Let's never cry again together.